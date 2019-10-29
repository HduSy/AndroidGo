package com.example.eventlistenertest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class PlainView extends View {
	float currentX, currentY;
	private Paint p = new Paint();
	private Bitmap plain0, plain1;
	private int index;

	public PlainView(Context context) {
		super(context);
		plain0 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
		plain1 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_round);
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				index++;
				PlainView.this.invalidate();
			}
		}, 0L, 100L);
		setFocusable(true);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(index % 2 == 0 ? plain0 : plain1, currentX, currentY, p);
	}
}
