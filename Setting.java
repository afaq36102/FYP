package com.example.afaq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Setting extends AppCompatActivity implements View.OnClickListener {

	EditText text,text_1;
	Button button,button_1,button_2;
    TextView text_2,text_3;
    Handler handler=null;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_setting);
}

@Override
protected void onStart() {
	super.onStart();
	text =(EditText) findViewById(R.id.editText);
	text_1 =(EditText) findViewById(R.id.editText_1);
	
	
	button =(Button) findViewById(R.id.submit);
	button.setOnClickListener(this);
	
	button_1 =(Button) findViewById(R.id.back_home);
	button_1.setOnClickListener(this);
	
	button_2 =(Button) findViewById(R.id.submit_1);
	button_2.setOnClickListener(this);
	
	
	text_2=(TextView) findViewById(R.id.current_channel_id);
	text_2.setText(MainActivity.channel_id);
	text_3=(TextView) findViewById(R.id.current_channel_field);
	text_3.setText(MainActivity.channel_field);
	
	
}

@Override
public void onClick(View v) {
	switch (v.getId()){
		case R.id.submit:
			Toast.makeText(this,"Channel Id Changed",Toast.LENGTH_SHORT).show();
			MainActivity.channel_id=text.getText().toString();
			text_2.setText(text.getText().toString());
			
			break;
		case R.id.back_home:
			Intent intent =new Intent(this,MainActivity.class);
			startActivity(intent);
			break;
		case R.id.submit_1:
			Toast.makeText(this,"Channel field Changed",Toast.LENGTH_SHORT).show();
			MainActivity.channel_field=text_1.getText().toString();
			text_3.setText(text_1.getText().toString());
			break;
	}
	
}




}
