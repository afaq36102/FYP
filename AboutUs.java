package com.example.afaq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutUs extends AppCompatActivity implements View.OnClickListener{
Button button;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.about_us);
}

@Override
protected void onStart() {
	super.onStart();
	button =(Button) findViewById(R.id.back);
	button.setOnClickListener(this);
}

@Override
public void onClick(View v) {
	switch (v.getId()){
		case R.id.back:
			Intent intent= new Intent(this,MainActivity.class);
			startActivity(intent);
			break;
	}
}
}
