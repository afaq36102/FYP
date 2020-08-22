package com.example.afaq;

import android.content.*;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.android.volley.*;

public class Error_Response implements Response.ErrorListener {
	private Context context=null;
	private Intent intent=null;
	static final String error_occured="error_occured";
	Error_Response(Context context){
		this.context=context;
	}

@Override
public void onErrorResponse(VolleyError error) {
	intent=new Intent(error_occured);
	LocalBroadcastManager.getInstance(this.context).sendBroadcast(intent);
}


}
