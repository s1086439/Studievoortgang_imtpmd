package com.example.imtpmd1617.studievoortgang;


import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class GsonRequest<T> extends Request<T> {
    private final Gson gson = new Gson();
    private final Class<T> mClazz;
    private final Map<String, String> headers;
    private final Response.Listener<T> listener;
    private final Type mType;
    private final String TAG = "GsonRequest<T>";
    protected static final Gson DEFAULT_GSON = new Gson();
    Map<String,String> params = new HashMap<String, String>();


    /** Make a GET request and return a parsed object from JSON.
     * @param url URL of the request to make
     * @param clazz Relevant class object, for Gson's reflection
     * @param headers Map of request headers     */

    public GsonRequest(String url, Class<T> clazz, Map<String, String> headers, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, errorListener);
        this.mClazz = clazz;
        this.mType = null;
        this.headers = headers;
        this.listener = listener;
    }
    // Add constructor : (replace Class<T>  with Type ) (Zodat we een lijst krijgen i.p.v. 1 class )
    public GsonRequest(String url, Type type, Map<String, String> headers, Map<String, String> params, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Request.Method.POST, url, errorListener);
        this.mClazz = null;
        this.mType = type;
        this.params = params;
        this.headers = headers;
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }


    @Override
    protected Map<String,String> getParams(){
        //Map<String,String> params = new HashMap<String, String>();
        //params.put("name", "student");
        //params.put("password", "wachtwoord");
        return this.params;
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            if(response.statusCode == 200) {
                String json = new String(response.data,HttpHeaderParser.parseCharset(response.headers));

                if (mClazz != null) {
                    return Response.success(gson.fromJson(json, mClazz), HttpHeaderParser.parseCacheHeaders(response));
                } else {
                    return (Response<T>) Response.success(gson.fromJson(json, mType), HttpHeaderParser.parseCacheHeaders(response));
                }
            }

            if(response.statusCode == 401){
                return Response.error(new ParseError());
            } else {
                return Response.error(new ParseError());
            }
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    protected Gson getGson() {
        return gson;
    }
}