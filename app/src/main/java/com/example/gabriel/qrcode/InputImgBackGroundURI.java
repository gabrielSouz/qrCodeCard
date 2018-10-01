package com.example.gabriel.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

import static com.example.gabriel.qrcode.MainActivity.TAG;

public class InputImgBackGroundURI extends Activity implements View.OnClickListener{
    public static final int PICK_IMAGE = 1;
    private Button botao;
    private ImageView imgBack;
    private Button botaoOk;
    public static Uri uriAtrr = null;
    Bitmap bitmap = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagem_back_ground);
        botao = (Button)findViewById(R.id.buttonPick);
        botaoOk =(Button) findViewById(R.id.buttonOK);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        botao.setOnClickListener(this);
        botaoOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(InputImgBackGroundURI.this,InputSite.class);
                i1.setData(uriAtrr);
                i1.putExtra("imgH",bitmap.getHeight());
                i1.putExtra("imgW",bitmap.getWidth());
                startActivity(i1);



            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE) {
            Uri uri = data.getData();


            try {
               bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
               botaoOk.setEnabled(true);
            }catch (IOException ex) {

            }
            imgBack.setImageBitmap(bitmap);
            uriAtrr = uri;

        }
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

    }
}
