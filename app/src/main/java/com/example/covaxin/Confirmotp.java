package com.example.covaxin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.HashMap;

public class Confirmotp extends AppCompatActivity {
    Button btn;
    TextInputEditText tinput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmotp);
        btn = (Button) findViewById(R.id.Cofirm_otp_btn);
        tinput = (TextInputEditText) findViewById(R.id.confirm_textbox);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                    String confirmOTPurl = "https://cdn-api.co-vin.in/api/v2/auth/public/confirmOTP";
                    HashMap<String, String> params = new HashMap<>();
                    String getValFromOTP = getIntent().getExtras().getString("txid");
                    params.put("otp", sha256(tinput.getText().toString()));
                    params.put("txnId", getValFromOTP);
                    JsonObjectRequest confirmrequest = new JsonObjectRequest(Request.Method.POST, confirmOTPurl, new JSONObject(params)
                            , new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Intent in = new Intent(Confirmotp.this, IDProofDetails.class);
                            startActivity(in);

                            finish();
                            Toast.makeText(Confirmotp.this, response.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Confirmotp.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    singletone.getInstance(Confirmotp.this).addToRequestQueue(confirmrequest);

                }
            }
            private boolean validation() {
                if (tinput.length() == 0) {
                    tinput.setError("Filed is empty");
                    return false;
                }
                return true;
            }
        });
    }
    private static String sha256(final String base) {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}