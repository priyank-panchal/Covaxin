package com.example.covaxin;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.textfield.TextInputEditText;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
public class SendOTP extends AppCompatActivity {
    Button btn;
    TextInputEditText tinput;
    String Textid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);
        btn = (Button)findViewById(R.id.Send_OTP);
        tinput =(TextInputEditText) findViewById(R.id.otpnumber);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                    String OTPRequest = "https://cdn-api.co-vin.in/api/v2/auth/public/generateOTP";
                    HashMap<String, String> param = new HashMap<>();
                    param.put("mobile", tinput.getText().toString());
                    JsonObjectRequest json = new JsonObjectRequest(Request.Method.POST, OTPRequest, new JSONObject(param),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    //JSONObject obj =response.getJSONObject(0);
                                    try {
                                        Textid = response.getString("txnId");
                                        Intent in = new Intent(SendOTP.this, Confirmotp.class);
                                        in.putExtra("txid",Textid);
                                        in.putExtra("mobile",tinput.getText().toString());
                                        startActivity(in);
                                        finish();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(SendOTP.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("accept", "application/json");
                            params.put("Content-Type", "application/json");
                            return params;
                        }
                    };
                    singletone.getInstance(SendOTP.this).addToRequestQueue(json);
                }
            }
        });
    }
    private boolean validation(){
        if(tinput.length() == 0 ){
            tinput.setError("Filed is empty");
            return false;
        }
        else if(tinput.length() != 10) {
            tinput.setError("Please Enter the valide length of mobile number");
            return false;
        }
        return true;
    }
}