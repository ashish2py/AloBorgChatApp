package com.developerbyweekend.aloborg.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.developerbyweekend.aloborg.access.AppSharedPreference;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ashish on 13/01/17.
 */

public class APIService  {
    private static RequestQueue queue = null;
    private String TAG="TAG";
    private AppSharedPreference appSharedPreference;

    private static RequestQueue getRequestQueue(Context context) {
        if (APIService.queue == null) {
            APIService.queue = Volley.newRequestQueue(context);
        }
        return APIService.queue;
    }

    public <T> void addToRequestQueue(Context context, Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue(context).add(req);
    }

    public <T> void addToRequestQueue(Context context, Request<T> req) {
        req.setTag(TAG);
        getRequestQueue(context).add(req);
    }

    // cancel pending ques
    public void cancelPendingRequests(Object tag) {
        if (queue  != null) {
            queue .cancelAll(tag);
        }
    }

    // logout from app and clear shared preference
    public void logout(Context context){
        appSharedPreference.clearApplicationContext(context);

    }

    // GET REQUEST PROCESSING
    public static void get(Context context, String url, final Map<String,String> headers, final APIReceiver receiver){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url, null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response){
                receiver.onResponse(response);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.networkResponse != null && error.networkResponse.data != null){
                    receiver.onError(new Exception(new String(error.networkResponse.data)));
                }else{
                    receiver.onError(error);
                }
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if(headers == null)
                    return new HashMap<String, String>();
                return headers;
            }
        };
        APIService.getRequestQueue(context).add(request);
    }

    // POST REQUEST
    public static void post(Context context,String url, JSONObject body,final Map<String, String> headers, final APIReceiver receiver){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, body, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                receiver.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.networkResponse != null && error.networkResponse.data != null){
                    receiver.onError(new Exception(new String(error.networkResponse.data)));
                }else{
                    receiver.onError(error);
                }
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if(headers == null)
                    return new HashMap<String, String>();
                return headers;
            }
        };
        APIService.getRequestQueue(context).add(request);
    }

}
