package com.example.covaxin;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class ShowCertificate extends AppCompatActivity {
    ImageView mImageView;
    String token=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_certificate);
        mImageView  = (ImageView) findViewById(R.id.imageView);
        token = getIntent().getStringExtra("Token_no");
        String txtNumber = getIntent().getStringExtra("Ref_no");
        String url="https://cdn-api.co-vin.in/api/v2/registration/certificate/public/download?beneficiary_reference_id=" + txtNumber;
         StringRequest imgreq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ShowCertificate.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error is ", error.toString() + error);
            }
        }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                       String authValue = "Bearer " + token;
                       params.put("Authorization", authValue);
                       params.put("Content-Type ", "application/json");
                       params.put("accept ","application/pdf");
                      return params;
            }
        };
        singletone.getInstance(ShowCertificate.this).addToRequestQueue(imgreq);
    }
}
