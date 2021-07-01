package com.example.covaxin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShowDetailsUser extends AppCompatActivity {
    Button btn,btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_user);
        btn = (Button) findViewById(R.id.scheduleApp);
        btn1= (Button) findViewById(R.id.ADD_More);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ShowDetailsUser.this,SearchByPin.class);
                startActivity(in);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ShowDetailsUser.this,IDProofDetails.class);
                startActivity(in);
            }
        });
    }
}