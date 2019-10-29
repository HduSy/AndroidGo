package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Timer;

public class AidlService extends Service {
	private Timer timer = new Timer();
	private String[] colors = new String[]{"红色", "黄色", "黑色"};
	private double[] weights = new double[]{2.3, 3.1, 1.58};
	private String color;
	private double weight;
	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
