package com.example.mainactivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public class FrameActivity extends Activity {
	int[] names = new int[]{
			R.id.view01, R.id.view02, R.id.view03, R.id.view04, R.id.view05, R.id.view06
	};
	TextView[] views = new TextView[names.length];
	private Handler handler= new MyHandler(new WeakReference<FrameActivity>(this));
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frame);
		for (int i = 0; i < views.length; i++) {
			views[i] = findViewById(names[i]);
		}
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				handler.sendEmptyMessage(0x123);
			}
		}).start();*/
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				handler.sendEmptyMessage(0x123);
			}
		},0 ,200 );
	}
	class MyHandler extends Handler{
		private WeakReference<FrameActivity> activityWeakReference;
		MyHandler(WeakReference<FrameActivity> activityWeakReference){
			this.activityWeakReference = activityWeakReference;
		}
		int[] colors = new int[]{
				R.color.red,R.color.green,R.color.blue,R.color.pink,R.color.purple,R.color.yellow
		};
		private int currentColor = 0;

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0x123){
				for (int i = 0;i<activityWeakReference.get().names.length;i++){
					activityWeakReference.get().views[i].setBackgroundResource(colors[(i+currentColor)%colors.length]);
				}
				currentColor++;
			}
			super.handleMessage(msg);
		}
	}
}
