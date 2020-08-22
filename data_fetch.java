package com.example.afaq;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class data_fetch extends Request<JSONObject> {
JSONObject obj=null;
Context context=null;
Intent intent=null;
static String error_occured="error_occured";
Response_listener listener_1=null;
public data_fetch(int method, String url, Response_listener listener_1, Error_Response listener, Context context) {
	super(method, url, listener);
	this.listener_1=listener_1;
//	setRetryPolicy(new DefaultRetryPolicy(1000,1,1.0f));
	this.context=context;
}



@Override
protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
	try {
		String jsonString =
				new String(
						response.data);
		return Response.success(
				new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
	}
	catch (JSONException je) {
		intent=new Intent(error_occured);
		LocalBroadcastManager.getInstance(this.context).sendBroadcast(intent);
		return Response.error(new ParseError(je));
	}
	
}

@Override
protected void deliverResponse(JSONObject response) {

	listener_1.onResponse(response,this.context);
	
}



}
