package com.example.afaq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class lay extends AppCompatActivity implements View.OnClickListener {
Button button;
Button button_1;
Button button_2;




@Override
protected void onCreate(final Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.menu_file);
	
	

	
}

@Override
protected void onStart() {
	super.onStart();
	button =(Button) findViewById(R.id.home_1);
	button.setOnClickListener(this);
	
	button_1 =(Button) findViewById(R.id.about_1);
	button_1.setOnClickListener(this);
	
	button_2 =(Button) findViewById(R.id.setting_1);
	button_2.setOnClickListener(this);
	
}

@Override
public void onClick(View v1) {
	switch (v1.getId()){
		case R.id.home_1 :
			Intent intent =new Intent(this,MainActivity.class);
			startActivity(intent);
			break;
			
		case R.id.about_1:
			Intent intent_1 =new Intent(this,AboutUs.class);
			startActivity(intent_1);
			break;
		case R.id.setting_1:
			Intent intent_2 =new Intent(this,Setting.class);
			startActivity(intent_2);
			break;
		
	}
	
}


}
