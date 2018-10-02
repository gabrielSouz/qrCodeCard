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



public class InputImgBackGroundURI extends Activity {
    public static final int PICK_IMAGE = 1;
    private Button btnChooseIMG;
    private ImageView imgBack;
    private Button btnOK;

    //Vars to persiste to btnOK which send data do next activity
    Bitmap imgBitmap;
    Uri imgUri;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagem_back_ground);
        btnChooseIMG = findViewById(R.id.buttonPick);
        btnOK = findViewById(R.id.buttonOK);
        imgBack =  findViewById(R.id.imgBack);
        configBtns();

    }


    private void configBtns(){
        btnChooseIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });



        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(InputImgBackGroundURI.this,InputSite.class);
                i1.setData(imgUri);
                i1.putExtra("imgH",imgBitmap.getHeight());
                i1.putExtra("imgW",imgBitmap.getWidth());
                startActivity(i1);



            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE) {
            imgUri = data.getData();
            imgBitmap = null;

            try {
                imgBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUri);
               btnOK.setEnabled(true);
            }catch (IOException ex) {

            }
            imgBack.setImageBitmap(imgBitmap);


        }
    }



}
