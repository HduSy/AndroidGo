package com.example.chapter10;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.service.BetterIntentService;
import com.example.service.NormalService;

public class IntentServiceActivity extends AppCompatActivity {
	Button btnService, btnIntentService;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intent_service);
		btnService = findViewById(R.id.btnS);
		btnIntentService = findViewById(R.id.btnIS);
		btnService.setOnClickListener(v -> {
			Intent intent = new Intent(IntentServiceActivity.this, NormalService.class);
			startService(intent);
		});
		btnIntentService.setOnClickListener(v -> {
			Intent intent = new Intent(IntentServiceActivity.this, BetterIntentService.class);
			startService(intent);
		});
	}
}
