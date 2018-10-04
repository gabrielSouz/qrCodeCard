package com.example.gabriel.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class ConfirmingFinalActivity extends Activity {
    ImageView imgView1;
    Bitmap imgFinal1;
    File file1;
    Button btnOk;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirming_final);
        imgView1 = findViewById(R.id.ImgFinalQrLayout);
        btnOk = findViewById(R.id.buttonOk);
        btnCancel = findViewById(R.id.buttonCancel);
        btnOk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                boolean writeMediaExterna = isExternalStorageWritable();
                if(writeMediaExterna){

                try{
                   //catch image from imgfinal1 and write in folder downloads
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    imgFinal1.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] imageBytes2 = stream.toByteArray();
                    file1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    File completeFile = new File(file1.toString(),"imagemfinal.png");
                    completeFile.createNewFile();
                    FileOutputStream out = new FileOutputStream(completeFile);
                    out.write(imageBytes2);
                    out.close();
                    stream.close();
                }catch (Exception e){
                   e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),"See Folder Download",Toast.LENGTH_LONG
                ).show();}
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InputImgBackGroundURI.class);
                startActivity(intent);

            }
        });

        try{

            //get image name: image final and show in imgFinal1
            FileInputStream inStream1 = this.openFileInput("image_final");
            BitmapFactory.Options o = new BitmapFactory.Options();
            imgFinal1 =  BitmapFactory.decodeStream(inStream1, null, o);

        }catch (Exception e){

        }
        imgView1.setImageBitmap(imgFinal1);
    }


    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }


}
