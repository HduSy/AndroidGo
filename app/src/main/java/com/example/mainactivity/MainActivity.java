package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.self_ui.CircleDraw;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		RelativeLayout relativeLayout = findViewById(R.id.rl);
		CircleDraw circleDraw = new CircleDraw(this);
		circleDraw.setMinimumWidth(300);
		circleDraw.setMinimumHeight(500);
		relativeLayout.addView(circleDraw);
	}
	public void clickHandler(View source){
		TextView tv = findViewById(R.id.show);
		tv.setText("Hello Android-"+new java.util.Date());
	}
}
