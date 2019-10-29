package com.example.chapter9;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
	private Uri uri = Uri.parse("content://com.example.chapter9.firstprovider");

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_provider);
	}

	public void query(View view) {
		Cursor c = getContentResolver().query(uri, null, "query_where", null, null);
		Toast.makeText(this, "远程ContentProvider返回的Cursor为:" + c, Toast.LENGTH_LONG).show();
	}

	public void insert(View view) {
		ContentValues values = new ContentValues();
		values.put("name", "TheSy");
		Uri newUri = getContentResolver().insert(uri, values);
		Toast.makeText(this, "远程ContentProvider新插入记录的Uri为:" + newUri, Toast.LENGTH_LONG).show();
	}

	public void update(View view) {
		ContentValues values = new ContentValues();
		values.put("name", "TheSy");
		int count = getContentResolver().update(uri, values, "update_where", null);
		Toast.makeText(this, "远程ContentProvider更新记录数为:" + count, Toast.LENGTH_LONG).show();
	}

	public void delete(View view) {
		int count = getContentResolver().delete(uri, "delete_where", null);
		Toast.makeText(this, "远程ContentProvider删除记录数为:" + count, Toast.LENGTH_LONG).show();
	}
}
