package com.example.gabriel.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;




public class MainActivity extends Activity implements View.OnClickListener {

    private Button botao;
    public static final String TAG = "qrcodeappff7";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botao = findViewById(R.id.buttonStart);
        botao.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getApplicationContext(), InputImgBackGroundURI.class);
        startActivity(intent);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}