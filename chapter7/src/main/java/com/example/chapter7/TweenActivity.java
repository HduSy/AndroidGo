package com.example.chapter7;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public class TweenActivity extends Activity {
	private ImageView img;
	private Animation reverse,anim;
	private Button btn;

	class MyHandler extends Handler {
		private WeakReference<TweenActivity> activity;

		public MyHandler(WeakReference<TweenActivity> activity) {
			this.activity = activity;
		}

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0x123) {
				activity.get().img.startAnimation(activity.get().reverse);
			}
		}
	}

	Handler handler = new MyHandler(new WeakReference<>(this));

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tween_anim);
		img = findViewById(R.id.iv);
		btn = findViewById(R.id.btn);
		anim = AnimationUtils.loadAnimation(this, R.anim.anim);
		anim.setFillAfter(true);
		reverse = AnimationUtils.loadAnimation(this, R.anim.reverse);
		reverse.setFillAfter(true);
		btn.setOnClickListener(view -> {
			img.startAnimation(anim);
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					handler.sendEmptyMessage(0x123);
				}
			}, 3500);
		});
	}
}
