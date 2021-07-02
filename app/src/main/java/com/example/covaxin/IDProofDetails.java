package com.example.covaxin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class IDProofDetails extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idproof_details);
        Log.d("idproof ","jay maa meldi ");
        getIDList();
        btn=(Button) findViewById(R.id.ID_Add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(IDProofDetails.this,ShowDetailsUser.class);
                startActivity(in);
            }
        });
    }
    private void intiUI(){
        Log.d("idproof","jay vahnvati ma ");
        final AutoCompleteTextView customView = findViewById(R.id.customerTextView);
        ArrayList<String> customerList = getIDList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(IDProofDetails.this, android.R.layout.simple_spinner_item,customerList);
        customView.setAdapter(adapter);
        findViewById(R.id.customerSpinnerLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(IDProofDetails.this, customView.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private ArrayList<String> getIDList(){
        ArrayList<String> arylist = new ArrayList<>();
        arylist.add("Addhar card");
        arylist.add("Driving License");
        arylist.add("Voter ID");
        arylist.add("PAN Card");
        arylist.add("passport");
        return  arylist;
    }
}