package com.example.covaxin;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class DownloadCertifacte extends AppCompatActivity {
    String TokenNumber;
    Button btn;
    TextInputEditText txtinput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_certifacte);
        txtinput = (TextInputEditText)findViewById(R.id.ref_number);
        btn = (Button) findViewById(R.id.ref_submit);
        TokenNumber = getIntent().getStringExtra("Token_no");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(DownloadCertifacte.this,ShowCertificate.class);
                in.putExtra("Ref_no",txtinput.getText().toString());
                in.putExtra("Token_no",TokenNumber);
                Log.d("Meldi", TokenNumber);
                Log.d("Meldi",txtinput.getText().toString());
                startActivity(in);

            }
        });



    }
}