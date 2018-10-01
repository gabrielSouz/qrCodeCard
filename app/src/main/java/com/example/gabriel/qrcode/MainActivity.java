package com.example.gabriel.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.amazon.device.ads.AdLayout;
import com.amazon.device.ads.AdRegistration;
import com.amazon.device.ads.AdTargetingOptions;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button botao;
    public static final String TAG = "qrcodeappff7";
    public AdLayout AdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdRegistration.setAppKey("cf5a0c0fe39d410bbc70454cc6492a63");

        botao = (Button) findViewById(R.id.buttonStart);
        botao.setOnClickListener(this);
        this.AdView = new AdLayout(this);
        LinearLayout ly = findViewById(R.id.mainAct);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
       ly.addView(this.AdView,lp);
        AdTargetingOptions adOptions = new AdTargetingOptions();
        this.AdView.loadAd(adOptions);


    }


    @Override
    public void onClick(View view) {

        nextTela();

    }

    public void nextTela() {

        Intent intent = new Intent(getApplicationContext(), InputImgBackGroundURI.class);
        startActivity(intent);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.AdView.destroy();
    }

}