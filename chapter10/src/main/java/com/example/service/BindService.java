package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class BindService extends Service {
	private int count;
	private boolean quit;
	private MyBinder binder = new MyBinder();

	public class MyBinder extends Binder {
		public int getCount() {
			return BindService.this.count;
		}
	}


	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("Service is binded");
		return binder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("Service is created");
		new Thread(() -> {
			while (!quit) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				BindService.this.count++;
			}
		}).start();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		System.out.println("Service is Unbinded");
		return true;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		this.quit = true;
		System.out.println("Service is destroyed");
	}
}
