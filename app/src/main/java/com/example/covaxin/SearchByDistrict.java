package com.example.covaxin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchByDistrict extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btn;
    Spinner spn;
    Spinner spn1;
    ArrayAdapter<String> adapter;
    Map<String,Integer> pairData;
    ArrayList<String> arylist;
    ArrayList<String> districtelist;
    ArrayAdapter adapter1;
    ArrayAdapter arrayAdapter2;
    Map<String,Integer> pairDataDistrict;
    Integer IdofDistricte = 0;
    ArrayList<vaccineSlotsDistricte> vaccineSlots;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_district);
        btn = (Button) findViewById(R.id.Search_button);
        spn = (Spinner) findViewById(R.id.SearchbyState);
        spn1 = (Spinner) findViewById(R.id.SearchbyDistrict);
        lv = (ListView) findViewById(R.id.lstview);
        String stateNameApi = "https://cdn-api.co-vin.in/api/v2/admin/location/states";
        JsonObjectRequest state = new JsonObjectRequest(Request.Method.GET, stateNameApi, null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                arylist = new ArrayList<>();
                pairData = new HashMap<String, Integer>();
                try {
                    JSONArray obj = response.getJSONArray("states");
                    for (int i = 0; i < obj.length(); ++i) {
                        JSONObject withInObj = obj.getJSONObject(i);
                        Integer state_id = withInObj.getInt("state_id");
                        String stateName = withInObj.getString("state_name");
                        pairData.put(stateName, state_id);
                        arylist.add(stateName);
                        adapter = new ArrayAdapter<>(SearchByDistrict.this,
                                android.R.layout.simple_spinner_item, arylist);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spn.setAdapter(adapter);
                    }
                    spn.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SearchByDistrict.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        singletone.getInstance(SearchByDistrict.this).addToRequestQueue(state);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String last = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict?district_id="+IdofDistricte.toString()+"&date=19-07-2021";

                JsonObjectRequest SearchByDis = new JsonObjectRequest(Request.Method.GET,
                        last,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                vaccineSlots = new ArrayList<>();
                                vaccineSlotsDistricte VS = new vaccineSlotsDistricte();
                                try {
                                    JSONArray obj = response.getJSONArray("sessions");
                                    for (int i = 0; i < obj.length(); ++i) {
                                        JSONObject withInObj = obj.getJSONObject(i);
                                        VS.setName(withInObj.getString("name"));
                                        VS.setAddress(withInObj.getString("address"));
                                        VS.setDistrict_name(withInObj.getString("district_name"));
                                        VS.setBlock_name(withInObj.getString("block_name"));
                                        VS.setState_name(withInObj.getString("state_name"));
                                        VS.setPincode(withInObj.getInt("pincode"));
                                        VS.setFrom(withInObj.getString("from"));
                                        VS.setTo(withInObj.getString("to"));
                                        VS.setFee_type(withInObj.getString("fee_type"));
                                        VS.setDate(withInObj.getString("date"));
                                        VS.setAvailable_capacity(withInObj.getInt("available_capacity"));
                                        VS.setAvailable_capacity_dose1(withInObj.getInt("available_capacity_dose1"));
                                        VS.setAvailable_capacity_dose2(withInObj.getInt("available_capacity_dose2"));
                                        VS.setMin_age_limit(withInObj.getInt("min_age_limit"));
                                        VS.setVaccine(withInObj.getString("vaccine"));
                                        vaccineSlots.add(VS);
                                        arrayAdapter2 = new ArrayAdapter(SearchByDistrict.this, android.R.layout.simple_list_item_1, vaccineSlots);
                                    }
                                    lv.setAdapter(arrayAdapter2);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SearchByDistrict.this, "Not found data", Toast.LENGTH_SHORT).show();
                    }
                });
                singletone.getInstance(SearchByDistrict.this).addToRequestQueue(SearchByDis);

            }
        });
        spn.setOnItemSelectedListener(this);
        spn1.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == R.id.SearchbyState){
            String selectedState = parent.getSelectedItem().toString();
            Integer districteId = pairData.get(selectedState);
            String Districterequest = "https://cdn-api.co-vin.in/api/v2/admin/location/districts/" + districteId.toString();
            JsonObjectRequest district_req = new JsonObjectRequest(Request.Method.GET, Districterequest, null
                    , new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    districtelist = new ArrayList<>();
                    pairDataDistrict = new HashMap<String, Integer>();

                    try{
                        JSONArray obj = response.getJSONArray("districts");
                        for(int i = 0 ; i < obj.length(); ++i) {
                            JSONObject withInObj = obj.getJSONObject(i);
                            String dis_name = withInObj.getString("district_name");
                            Integer districteID  = withInObj.getInt("district_id");
                            pairDataDistrict.put(dis_name,districteID);
                            districtelist.add(dis_name);
                           adapter1 = new ArrayAdapter<>(SearchByDistrict.this,
                                    android.R.layout.simple_spinner_item,districtelist);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spn1.setAdapter(adapter1);
                        }
                        spn1.setAdapter(adapter1);
                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(SearchByDistrict.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            singletone.getInstance(SearchByDistrict.this).addToRequestQueue(district_req);
        }
        else if(parent.getId() == R.id.SearchbyDistrict){
            String selectedDistrict = parent.getSelectedItem().toString();
            IdofDistricte = pairDataDistrict.get(selectedDistrict);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


}