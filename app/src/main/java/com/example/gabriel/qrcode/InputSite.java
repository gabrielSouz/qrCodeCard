package com.example.gabriel.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputSite extends Activity {
    EditText txtSite;
    Button cmdOk;
    String site;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_site);
        Intent i1 = getIntent();
        uri = i1.getData();
        txtSite = findViewById(R.id.txtSite);
        cmdOk = findViewById(R.id.cmdOkSite);
        cmdOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                site = txtSite.getText().toString();
                Intent i1 = new Intent(InputSite.this,DrawQrImageAbove.class);
                i1.putExtra("site",site);
                i1.setData(uri);

                startActivity(i1);

            }
        });

    }

}
