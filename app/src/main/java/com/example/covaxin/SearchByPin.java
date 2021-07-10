package com.example.covaxin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchByPin extends AppCompatActivity {
    Button btn;
    TextInputLayout tinput;
    ArrayList<VaccineSlots> vaccineSlots;
    ListView lv;
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_pin);
        btn = (Button) findViewById(R.id.Search);
        tinput = (TextInputLayout) findViewById(R.id.editTextPhone);
        lv =(ListView) findViewById(R.id.lstview);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SearchRequesturl = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode="+ tinput.getEditText().getText().toString()  +"&date=10-07-2021";
                JsonObjectRequest searchbypin = new JsonObjectRequest(Request.Method.GET,
                        SearchRequesturl,
                        null,
                        new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        vaccineSlots = new ArrayList<>();
                        VaccineSlots VS = new VaccineSlots();
                        try {
                            JSONArray obj = response.getJSONArray("sessions");
                            for (int i = 0; i < obj.length(); ++i) {
                                JSONObject withInObj = obj.getJSONObject(i);
                                VS.setName(withInObj.getString("name"));
                                VS.setAddress(withInObj.getString("address"));
                                VS.setDistrict_name(withInObj.getString("district_name"));
                                VS.setBlock_name(withInObj.getString("block_name"));
                                VS.setPincode(withInObj.getInt("pincode"));
                                VS.setFrom(withInObj.getString("from"));
                                VS.setTo(withInObj.getString("to"));
                                VS.setFee_type(withInObj.getString("fee_type"));
                                VS.setDate(withInObj.getString("date"));
                                VS.setAvailable_capacity(withInObj.getInt("available_capacity"));
                                VS.setAvailable_capacity_dose1(withInObj.getInt("available_capacity_dose1"));
                                VS.setAvailable_capacity_dose2(withInObj.getInt("available_capacity_dose2"));
                                VS.setMin_age_limit(withInObj.getInt("min_age_limit"));
                                vaccineSlots.add(VS);
                                arrayAdapter = new ArrayAdapter(SearchByPin.this, android.R.layout.simple_list_item_1, vaccineSlots);
                            }
                            lv.setAdapter(arrayAdapter);
                        }
                        catch (JSONException e){
                        e.printStackTrace();
                }
              }

           },new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(SearchByPin.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
                singletone.getInstance(SearchByPin.this).addToRequestQueue(searchbypin);
        }
        });
    }
}

