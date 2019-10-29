package com.example.chapter7;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class GestureActivity extends Activity {
	private ImageView imageView;
	private GestureDetector gestureDetector;
	private Bitmap bitmap;
	private int w, h;
	private float currentScale = 1.0f;
	private Matrix matrix;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tween_anim);
		/**
		 * 设置GestureDetector对象
		 */
		gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				float vx = velocityX > 4000 ? 4000f : velocityX;
				currentScale += currentScale * vx / 4000.0f;
				currentScale = currentScale > 0.01 ? currentScale : 0.01f;
				matrix.reset();
				BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
				if (!bitmapDrawable.getBitmap().isRecycled()) {
					bitmapDrawable.getBitmap().recycle();
				}
				Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
				imageView.setImageBitmap(bitmap1);
				return true;
			}
		});
//设置初始化图片通过BitmapFactory.decodeResource
		imageView = findViewById(R.id.iv);
		matrix = new Matrix();
		bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
		w = bitmap.getWidth();
		h = bitmap.getHeight();
		imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
	}

	/**
	 * 将activity上的时间传给GestureDetector对象处理
	 *
	 * @param event
	 * @return
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return gestureDetector.onTouchEvent(event);
	}
}
