package com.example.afaq;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NoCache;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Myservice extends Service {

IBinder binder = new LocalService();

@Override
public IBinder onBind(final Intent intent) {
	return this.binder;
}



class LocalService extends Binder {
	Myservice getservice(){
		
		return Myservice.this;
	}
}

void download_data(final Context context){
	  String url_1="https://api.thingspeak.com/channels/";
	  String channel_id_url=MainActivity.channel_id;
	  String end_part_url="/fields/";
	 String field_part=MainActivity.channel_field;
	 String end_url=".json?results=10";
	 String final_url=url_1.concat(channel_id_url).concat(end_part_url).concat(field_part).concat(end_url);
	
	 RequestQueue queue =Volley.newRequestQueue(context);
	 data_fetch data=new data_fetch(Request.Method.GET,final_url,new Response_listener(),new Error_Response(context),context);
queue.add(data);

}


}
