package com.example.gabriel.qrcode;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.util.AttributeSet;

import android.view.MotionEvent;
import android.view.View;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import static com.example.gabriel.qrcode.MainActivity.TAG;

public class CustomView extends View {
    private Bitmap fundoBitMap;
    private ArrayList pts;
    int i = 0;
    private Paint paint1;
    private float touchx = 0.0f;
    private float touchy = 0.0f;
    private int maqEstadoRect = 0;
    private float[] ponto1 = new float[2];
    private float[] ponto2 = new float[2];
    private Bitmap qrcodeDeseho;
    private String urlSite = "";
    private Bitmap imagemCanvasFinal;

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }


    public CustomView(Context context) {
        super(context);
        setDrawingCacheEnabled(true);
        paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setColor(Color.GRAY);
        paint1.setStyle(Paint.Style.FILL);
        pts = new ArrayList();
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawBitmap(fundoBitMap, 0, 0, new Paint());
        switch (maqEstadoRect) {
            case 0:

                break;
            case 1:
                ponto1[0] = touchx;
                ponto1[1] = touchy;
                canvas.drawCircle(ponto1[0], ponto1[1], 30, paint1);


                break;
            case 2:
                ponto2[0] = touchx;
                ponto2[1] = touchy;
                canvas.drawCircle(ponto1[0], ponto1[1], 30, paint1);
                canvas.drawCircle(ponto2[0], ponto2[1], 30, paint1);
                canvas.drawRect(ponto1[0], ponto1[1], ponto2[0], ponto2[1], paint1);
                float wRect = ponto2[0] - ponto1[0];
                float hRect = ponto2[1] - ponto1[1];
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                qrcodeDeseho = drawQrCodeInRect(urlSite, wRect, hRect);
                canvas.drawBitmap(fundoBitMap, 0, 0, new Paint());
                canvas.drawBitmap(qrcodeDeseho, ponto1[0], ponto1[1], paint1);
                salvarBitmap();
                nextTela();
                break;
        }

    }

    public void setFundoBitMap(Bitmap fundoBitMap) {
        this.fundoBitMap = fundoBitMap;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchx = event.getX();
                touchy = event.getY();
                maqEstadoRect++;
                invalidate();
                return true;


        }

        return false;

    }

    public Bitmap drawQrCodeInRect(String urlSring, float w, float h) {
        QRCodeWriter writer = new QRCodeWriter();

        try {
            String content = urlSring;
            BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, (int) w, (int) h);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap((int) w, (int) h, Bitmap.Config.RGB_565);
            for (int x = 0; x < (int) w; x++) {
                for (int y = 0; y < (int) h; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            return bmp;

        } catch (Exception e) {

        }
        return null;

    }

    public void setUrlSite(String urlSite) {
        this.urlSite = urlSite;
    }

    public void salvarBitmap() {
        imagemCanvasFinal = getDrawingCache();

        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imagemCanvasFinal.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] imageBytes = stream.toByteArray();

            FileOutputStream stream1 = getContext().openFileOutput("image_final", Context.MODE_PRIVATE);


            stream1.write(imageBytes);
            stream.close();
            stream.close();
        } catch (Exception ex) {


        }

    }


    public void nextTela(){

        Intent intent = new Intent(getContext(), ConfirmingFinalActivity.class);
       getContext().startActivity(intent);


    }
}