package com.example.chapter7;

import android.app.Activity;
import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.IOException;

public class ImageDecoderActivity extends Activity {
	ImageView imageView;
	TextView textView;
	@RequiresApi(api = Build.VERSION_CODES.P)
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_decoder);
		imageView = findViewById(R.id.img);
		textView = findViewById(R.id.tv);
		ImageDecoder.Source source = ImageDecoder.createSource(getResources(), R.drawable.ic_launcher_background);
		try {
			Drawable drawable = ImageDecoder.decodeDrawable(source, (decoder, info, s) -> {
				textView.setText("图片原始宽度"+info.getSize().getWidth()+"\n图片原始高度"+info.getSize().getHeight());
				decoder.setTargetSize(600, 580);
			});
			imageView.setImageDrawable(drawable);
			if (drawable instanceof AnimatedImageDrawable){
				((AnimatedImageDrawable) drawable).start();
			}
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
