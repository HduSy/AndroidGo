package com.example.eventlistenertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
//TODO: 启动不了闪退
public class ActivityEventListener extends AppCompatActivity {
	private int speed = 10;
	private PlainView plainView;
	DisplayMetrics metrics;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		plainView = new PlainView(this);
		setContentView(plainView);
		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		plainView.currentX = (metrics.widthPixels / 2);
		plainView.currentY = (metrics.heightPixels - 200);
		plainView.setOnTouchListener(new MyTouchListener());
	}

	class MyTouchListener implements View.OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getX() < metrics.widthPixels / 8) {
				plainView.currentX -= speed;
			}
			if (event.getX() > metrics.widthPixels * 7 / 8) {
				plainView.currentX += speed;
			}
			if (event.getY() < metrics.widthPixels / 8) {
				plainView.currentY -= speed;
			}
			if (event.getY() > metrics.widthPixels * 7 / 8) {
				plainView.currentY += speed;
			}
			return true;
		}
	}
}
