package com.example.afaq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.JsonObjectRequest;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
protected boolean data_fetching=true;
private static GraphView graph;
protected static  String channel_id="1082055";
protected static  String channel_field ="1";
my_data my_data_1 =new my_data();
protected  double [] data_received = new double[10];
private boolean internet_status = false;
private  boolean internet_status_1 = false;
private static LineGraphSeries<DataPoint> series;
Myservice obj_1=null;
protected Intent intent=null;
private boolean mbound=false;
private Button button;
private static  Button button_1;
private static TextView text,text_1;
private static TextView text_2;


@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.layout);
    this.intent =new Intent(this, Myservice.class);
    Log.d("tag","binding service");
    this.bindService(intent,this.connection, Context.BIND_AUTO_CREATE);
    Log.d("tag","service binded");
    
}

private boolean isNetworkConnected() {
    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    
    return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
}

@Override
protected void onStart() {
    super.onStart();
    internet_status=isNetworkConnected();
    button =(Button) findViewById(R.id.menu);
    button_1 =(Button) findViewById(R.id.connect);
    
    button.setOnClickListener(this);
    button_1.setOnClickListener(this);
    
    text = (TextView) findViewById(R.id.textView3);
    text_1 = (TextView) findViewById(R.id.textView5);
    text_2 = (TextView) findViewById(R.id.textView7);
    
    graph = (GraphView) findViewById(R.id.graph);
    graph.setVisibility(View.VISIBLE);
    
    LocalBroadcastManager.getInstance(this).registerReceiver(my_data_1,new IntentFilter(Response_listener.data_send));
    LocalBroadcastManager.getInstance(this).registerReceiver(my_data_1,new IntentFilter(Error_Response.error_occured));
    
}

void stop_async(){
    //Myservice.download.cancel(true);
}

 void set_values(){
     graph.removeAllSeries();
    series =new LineGraphSeries<DataPoint>();
    for(int i=0;i<data_received.length;i++){
        series.appendData(new DataPoint(i,data_received[i]),true,data_received.length);
        
    }
    graph.addSeries(series);
     Log.d("tag","values set");
    Double previous_value=data_received[8];
    String previous_value_text=String.valueOf(previous_value);
    text_2.setText(previous_value_text);
    Log.d("TAG","previous value".concat(previous_value_text));
    
    Double present_value=data_received[9];
    String present_value_text=String.valueOf(present_value);
    text.setText(present_value_text);
    Log.d("TAG","present value".concat(present_value_text));
    
    if(present_value<0.5){
        text_1.setText("No");
    }
    
    data_fetching=true;
    button_1.setEnabled(true);
    
}

@Override
protected void onDestroy() {
    super.onDestroy();
    unbindService(this.connection);
    mbound=false;
    
}




@Override
protected void onStop() {
    super.onStop();
    
    
    
}

@Override
public void onClick(View v) {
    switch (v.getId()){
        
        case R.id.menu:
            
            Intent intent_2 =new Intent(this,lay.class);
            startActivity(intent_2);
            break;
        case R.id.connect:
            internet_status=isNetworkConnected();
            
            if(!internet_status){
                Log.d("TAG","No Internet");
                Toast.makeText(this,"No Internet Availible",Toast.LENGTH_SHORT).show();
            }
            if(internet_status&&data_fetching) {
                Log.d("tag","channel id is ".concat(channel_id));
                Toast.makeText(this,"Trying To Connect",Toast.LENGTH_SHORT).show();
                data_fetching=false;
                //get_vlaue();
               // executorService.submit(new internet_error(getApplicationContext()));
            obj_1.download_data(getApplicationContext());
            }
            if(!data_fetching){
                Toast.makeText(this,"Already Connecting",Toast.LENGTH_SHORT).show();
            }
            break;
    }
}

 private ServiceConnection connection =new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        // Myservice.LocalServie obj = (Myservice.LocalServie) service;
        Log.d("tag","in service connection");
        Myservice.LocalService obj=null;
        obj = (Myservice.LocalService) service;
        obj_1 =obj.getservice();
        Log.d("tag","Service connected");
        mbound=true;
        
    }
    
    @Override
    public void onServiceDisconnected(ComponentName name) {
        mbound=false;
    }
};


class my_data extends BroadcastReceiver{
    
    @Override
    public void onReceive(Context context, Intent intent) {
if(Response_listener.data_send.equals(intent.getAction())){
    Toast.makeText(getApplicationContext(),"Connection Successfull",Toast.LENGTH_SHORT).show();
    double [] fetch_by=intent.getDoubleArrayExtra("values");
   data_received=fetch_by;
   set_values();
   
}
 if(Error_Response.error_occured.equals(intent.getAction())){
     Toast.makeText(getApplicationContext(),"Error in Connection",Toast.LENGTH_SHORT).show();
     data_fetching=true;
 }
 
    
    }
}

}
