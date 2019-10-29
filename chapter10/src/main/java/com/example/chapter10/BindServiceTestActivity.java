package com.example.chapter10;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.service.BindService;

public class BindServiceTestActivity extends AppCompatActivity {
	private BindService.MyBinder binder;
	Button bindBtn, unBindBtn, getCountBtn;
	ServiceConnection conn = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			System.out.println("=====Service Connected=====");
			binder = (BindService.MyBinder) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			System.out.println("=====Service Disconnected=====");
		}
	};

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bind_service);
		bindBtn = findViewById(R.id.bindBtn);
		unBindBtn = findViewById(R.id.unbindBtn);
		getCountBtn = findViewById(R.id.getBtn);
		Intent intent = new Intent(this, BindService.class);
		bindBtn.setOnClickListener(v -> {
			bindService(intent, conn, BIND_AUTO_CREATE);
		});
		unBindBtn.setOnClickListener(v -> {
			unbindService(conn);
		});
		getCountBtn.setOnClickListener(v -> {
			Toast.makeText(this, "Service中count的值为:" + BindServiceTestActivity.this.binder.getCount(), Toast.LENGTH_LONG).show();
		});
	}
}
