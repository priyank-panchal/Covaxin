package com.example.covaxin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

public class IDProofDetails extends AppCompatActivity  {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idproof_details);
        Log.d("idproof ","jay maa meldi ");
        btn=(Button) findViewById(R.id.ID_Add);
        intiUI();
        GenderUI();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(IDProofDetails.this,ShowDetailsUser.class);
                startActivity(in);
            }
        });
    }
    private void intiUI(){

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
    private void GenderUI(){
        final AutoCompleteTextView genderview = findViewById(R.id.ID_Gender1);
        ArrayList<String> genderlist = new ArrayList<>();
        genderlist.add("Male");
        genderlist.add("Female");
        genderlist.add("Other");
        ArrayAdapter<String> adpter = new ArrayAdapter<>(IDProofDetails.this, android.R.layout.simple_spinner_item,genderlist);
        genderview.setAdapter(adpter);
        findViewById(R.id.ID_Gender).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(IDProofDetails.this, genderview.getText(), Toast.LENGTH_SHORT).show();
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