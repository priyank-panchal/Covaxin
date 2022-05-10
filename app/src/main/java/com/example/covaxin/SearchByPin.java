package com.example.covaxin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.textfield.TextInputLayout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class SearchByPin extends AppCompatActivity {
    Button btn;
    TextInputLayout tinput;
    ArrayList<VaccineSlots> vaccineSlots;
    ListView lv;
    ArrayAdapter arrayAdapter;
    Button MovetoDistricte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_pin);
        btn = (Button) findViewById(R.id.Search);
        tinput = (TextInputLayout) findViewById(R.id.editTextPhone);
        lv =(ListView) findViewById(R.id.lstview);
        MovetoDistricte = (Button) findViewById(R.id.schedulesearchbydistrict);
        MovetoDistricte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(SearchByPin.this,SearchByDistrict.class);
                startActivity(in);
            }
        });
        Toast.makeText(this, getIntent().getExtras().getString("user"), Toast.LENGTH_SHORT).show();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SearchRequesturl = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode="+ tinput.getEditText().getText().toString()  +"&date=19-07-2021";
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
                            if(obj.length() != 0) {
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
                            else {
                                AlertDialog.Builder alert = new AlertDialog.Builder(SearchByPin.this).setTitle("Vacccine").setMessage("Not found Data");
                                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(SearchByPin.this, "", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                        catch (JSONException e){
                        e.printStackTrace();
                }
              }
           },new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(SearchByPin.this, "Data not Found", Toast.LENGTH_SHORT).show();
                }
            });
                singletone.getInstance(SearchByPin.this).addToRequestQueue(searchbypin);
        }
        });
       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              AlertDialog.Builder alert = new AlertDialog.Builder(SearchByPin.this);
              alert.setTitle("Vaccine");
              alert.setMessage("Schedule appoinment?").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                        VaccineSlots vc =vaccineSlots.get(position);
                        VaccineSlots newSlots = new VaccineSlots();
                        newSlots.setName(vc.getName());
                        newSlots.setFee_type(vc.getFee_type());
                        newSlots.setMin_age_limit(vc.getMin_age_limit());
                        newSlots.setDate(vc.getDate());
                        newSlots.setAvailable_capacity_dose1(vc.getAvailable_capacity_dose1());
                        newSlots.setAvailable_capacity_dose2(vc.getAvailable_capacity_dose2());
                        newSlots.setDose_1_status("Not Schedule");
                        newSlots.setDose_2_status("Not Schedule");
                        IDproofDatabaseOperation iDproofDatabaseOperation = new IDproofDatabaseOperation(SearchByPin.this);
                        boolean isValid = iDproofDatabaseOperation.AddSchedule(newSlots);
                        if(isValid == true){
                            Toast.makeText(SearchByPin.this, "SUCCESFULLY APPOINNMENT SUBMITED", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(SearchByPin.this, "Not valid", Toast.LENGTH_SHORT).show();
                        }
                  }
              });
            alert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(SearchByPin.this, "jay vahnvati ma ", Toast.LENGTH_SHORT).show();
                }
            });
             alert.show();
            }
        });
    }
}

