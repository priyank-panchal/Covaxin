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

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class IDProofDetails extends AppCompatActivity  {
    Button btn;
    AutoCompleteTextView IDName;
    AutoCompleteTextView gender_name;
    private TextInputEditText id_name;;
    private TextInputEditText id_number;
    private TextInputEditText id_year;
    private Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idproof_details);
        btn = (Button) findViewById(R.id.ID_Add);
        btn2 = (Button) findViewById(R.id.download_cer);
        IDName = (AutoCompleteTextView) findViewById(R.id.customerTextView);
        gender_name = (AutoCompleteTextView) findViewById(R.id.ID_Gender1);
        id_name = (TextInputEditText) findViewById(R.id.ID_name1);
        id_number = (TextInputEditText) findViewById(R.id.IDNumber1);
        id_year = (TextInputEditText) findViewById(R.id.ID_Year1);
        intiUI();
        GenderUI();
        btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ValidationonFiled()) {
                        try {
                            IDProofDetailsdb allFileds = new IDProofDetailsdb(
                                    IDName.getText().toString(),
                                    id_name.getText().toString(),
                                    id_number.getText().toString(),
                                    Integer.parseInt(id_year.getText().toString()),
                                    gender_name.getText().toString());
                            IDproofDatabaseOperation IDproofDatabaseOperation = new IDproofDatabaseOperation(IDProofDetails.this);
                            boolean success= IDproofDatabaseOperation.addOne(allFileds.getID_name(),
                                    allFileds.getID_num(), allFileds.getID_type(),
                                    allFileds.getYear(), allFileds.getGender());
                            if(success == true) {
                                Toast.makeText(IDProofDetails.this, "succesfully inserted", Toast.LENGTH_SHORT).show();
                               Intent in = new Intent(IDProofDetails.this, ShowDetailsUser.class);
                               startActivity(in);
                               finish();
                            }
                            else
                                Toast.makeText(IDProofDetails.this, "make some problem", Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception e){
                            Toast.makeText(IDProofDetails.this,"something missing ",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String OnesAgain = getIntent().getStringExtra("Token_no");
                Intent in = new Intent(IDProofDetails.this,DownloadCertifacte.class);
                in.putExtra("Token_no",OnesAgain);
                startActivity(in);
            }
        });

    }
    private Boolean ValidationonFiled() {
        Boolean isValid =true;
        if (IDName.length() == 0) {
            IDName.setError("This Field is Required");
            isValid=false;
        }
        if (gender_name.length() == 0) {
            gender_name.setError("this Field is Required");
            isValid = false;
        }
        String idproofname = id_name.getText().toString();
        if(idproofname.isEmpty()){
            id_name.setError("this field is Required");
            isValid =false;
        }
        String idproofnumber=id_number.getText().toString();
        System.err.println(idproofnumber);
        if(idproofname.isEmpty()){
            id_number.setError("this filed is required");
            isValid = false;
        }
        String idproofyear = id_year.getText().toString().trim();
        System.err.println(idproofname);
        if(idproofname.isEmpty()){
            id_year.setError("this filed is required");
            isValid = false;
        }
        return isValid;
    }
    private void intiUI(){
        final AutoCompleteTextView customView = findViewById(R.id.customerTextView);
        ArrayList<String> customerList = getIDList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(IDProofDetails.this, android.R.layout.simple_spinner_item,customerList);
        customView.setAdapter(adapter);
        findViewById(R.id.customerSpinnerLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(IDProofDetails.this, customView.getText().toString(), Toast.LENGTH_SHORT).show();
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