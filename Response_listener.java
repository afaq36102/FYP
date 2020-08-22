package com.example.afaq;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Response_listener implements Response_listener_interface{
private double[] a=new double[10];
	JSONObject obj=null;
	Context context=null;
	Intent intent=null;
	static final String data_send="data_send";
@Override
public void onResponse(JSONObject object, Context context) {
	this.context=context;
	obj=object;
		JSONArray array = null;
		try {
			array = obj.getJSONArray("feeds");
			Log.d("TAG","Getting feeds value");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		int lenght =array.length();
		Log.d("tag",String.valueOf(lenght));
		JSONObject [] obj_1 = new JSONObject[lenght];
		for(int index=0;index<lenght;index++){
			obj_1[index]=new JSONObject();
		}
		
		
		try {
			for(int j=0;j<lenght;j++){
				obj_1[j] = array.getJSONObject(j);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		try {
			for(int k=0;k<lenght;k++){
				this.a[k] = obj_1[k].getDouble("field1");
				System.out.println("value is "+k+" "+a[k]);
				
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	intent =new Intent(data_send);
		intent.putExtra("values",a);
	LocalBroadcastManager.getInstance(this.context).sendBroadcast(intent);
	
}
}
