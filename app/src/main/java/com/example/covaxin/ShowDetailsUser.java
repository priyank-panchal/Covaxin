package com.example.covaxin;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
public class ShowDetailsUser extends AppCompatActivity {
    Button btn,btn1;
    TextView tv;
    ListView lstview;
    MixActivity mixActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_user);
        btn1= (Button) findViewById(R.id.ADD_More);
        lstview = (ListView) findViewById(R.id.ltviw);
        List<IDProofDetailsdb>  lstId = new ArrayList<IDProofDetailsdb>();
        mixActivity = new MixActivity(ShowDetailsUser.this,lstId);
        lstview.setAdapter(mixActivity);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ShowDetailsUser.this,IDProofDetails.class);
                startActivity(in);
                finish();
            }
        });
    }
}