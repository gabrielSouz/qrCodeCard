package com.example.gabriel.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import android.os.Bundle;

import android.widget.LinearLayout;
import com.squareup.picasso.Picasso;


public class DrawQrImageAbove extends Activity implements com.squareup.picasso.Target {

    //CustomView is a Canvas For resize the QrCode Image
    CustomView view1;
    String urlSite="";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prototipo_touch);
        view1 = new CustomView(this);
        Intent i1 = getIntent();

        urlSite = i1.getStringExtra("site");
        
        Picasso.get().load(i1.getData()).into(this);

    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {


        //Here, the params for the customVIew handle the image
        view1.setFundoBitMap(bitmap);
        view1.setUrlSite(urlSite);
        addContentView(view1, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));

    }





    @Override
    public void onPrepareLoad(Drawable drawable) {

    }

    @Override
    public void onBitmapFailed(Exception e, Drawable drawable) {

    }
}
