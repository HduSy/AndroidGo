package com.example.chapter7;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
	Button btn;
	private String[] images;
	private int currentIndex;
	ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = findViewById(R.id.btn);
		try {
			images = getAssets().list("");
		} catch (IOException e) {
			e.printStackTrace();
		}
		btn.setOnClickListener(v -> {
			if (currentIndex >= images.length) {
				currentIndex = 0;
			}
			while (!images[currentIndex].endsWith(".png") && !images[currentIndex].endsWith(".jpg") && !images[currentIndex].endsWith(".gif")) {
				if (currentIndex >= images.length) {
					currentIndex = 0;
				}
			}
			InputStream assetFile = null;
			try {
				assetFile = getAssets().open(images[currentIndex++]);
			} catch (IOException e) {
				e.printStackTrace();
			}
			/**
			 * getDrawable()是View对象的方法
			 * BitmapDrawable包装Drawable
			 * getBitmap()是BitmapDrawable对象的方法
			 * isRecycled()是Bitmap对象的方法
			 */
			BitmapDrawable bd = (BitmapDrawable) iv.getDrawable();
//			Bitmap bd = ((BitmapDrawable) iv.getDrawable()).getBitmap()
			if (bd != null && !bd.getBitmap().isRecycled()) {
				bd.getBitmap().recycle();
			}
			iv.setImageBitmap(BitmapFactory.decodeStream(assetFile));
		});
	}
}
