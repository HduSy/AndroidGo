package com.example.chapter8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		preferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
		editor = preferences.edit();
		Button read = findViewById(R.id.read);
		Button write = findViewById(R.id.write);
		read.setOnClickListener(v -> {
			String time = preferences.getString("time", null);
			int randomNum = preferences.getInt("randNum", 0);
			String result = time == null ? "您暂时还未写入数据" : "写入时间为:" + time + "\n上次生成的随机数为:" + randomNum;
			Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
		});
		write.setOnClickListener(v -> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日" + "hhh:mm:ss");
			editor.putString("time", sdf.format(new Date()));
			editor.putInt("randNum", (int) (Math.random() * 100));
			editor.apply();
		});
	}
}
