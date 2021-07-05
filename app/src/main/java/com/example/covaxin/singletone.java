package com.example.covaxin;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class singletone {
    private static singletone instance;
    private RequestQueue requestQueue;
    private static Context ctx;
    private singletone(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }
    public static synchronized singletone getInstance(Context context) {
        if (instance == null) {
            instance = new singletone(context);
        }
        return instance;
    }
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
