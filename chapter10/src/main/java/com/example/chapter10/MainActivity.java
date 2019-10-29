package com.example.chapter10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.service.FirstService;

public class MainActivity extends AppCompatActivity {
	Button startBtn,stopBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startBtn = findViewById(R.id.startBtn);
		stopBtn = findViewById(R.id.stopBtn);
		final Intent intent = new Intent(this, FirstService.class);
		startBtn.setOnClickListener(v->startService(intent));
		stopBtn.setOnClickListener(v->stopService(intent));
	}
}
