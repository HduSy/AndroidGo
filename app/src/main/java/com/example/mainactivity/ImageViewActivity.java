package com.example.mainactivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ImageViewActivity extends AppCompatActivity {
	Button plus, minus, next;
	ImageView img01, img02;
	private int alpha = 255;
	private int currentImgIndex = 1;
	private int[] imgs = new int[]{
			R.mipmap.hero, R.mipmap.sagan, R.mipmap.venge
	};

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imageview);
		plus = findViewById(R.id.plus);
		minus = findViewById(R.id.minus);
		next = findViewById(R.id.next);
		img01 = findViewById(R.id.img01);
		img02 = findViewById(R.id.img02);
		View.OnClickListener listener = v -> {
			if (v == plus) {
				alpha += 20;
			} else if (v == minus) {
				alpha -= 20;
			}
			if (alpha > 255) {
				alpha = 255;
			}
			if (alpha < 0) {
				alpha = 0;
			}
			img01.setImageAlpha(alpha);
		};
		plus.setOnClickListener(listener);
		minus.setOnClickListener(listener);
		next.setOnClickListener(v -> img01.setImageResource(imgs[++currentImgIndex % imgs.length]));
		img01.setOnTouchListener((v, event) -> {
			BitmapDrawable bitmapDrawable = (BitmapDrawable) img01.getDrawable();
			Bitmap bitmap = bitmapDrawable.getBitmap();
			double scale = 1.0 * bitmap.getHeight() / img01.getHeight();
			long x = Math.round(event.getX() * scale);
			long y = Math.round(event.getY() * scale);
			if (x + 120 > bitmap.getWidth()) {
				x = bitmap.getWidth() - 120;
			}
			if (y + 120 > bitmap.getHeight()) {
				y = bitmap.getHeight() - 120;
			}
			img02.setImageBitmap(Bitmap.createBitmap(bitmap, (int) x, (int) y, 120, 120));
			img02.setImageAlpha(alpha);
			return true;
		});
	}
}
