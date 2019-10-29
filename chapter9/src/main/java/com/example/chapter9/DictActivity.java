package com.example.chapter9;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.content_provider.DictProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DictActivity extends Activity {
	EditText wordEdit, detailEdit, keyEdit;
	Button insertBtn, searchBtn;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dict);
		insertBtn = findViewById(R.id.insertBtn);
		searchBtn = findViewById(R.id.searchBtn);
		wordEdit = findViewById(R.id.wordEdit);
		detailEdit = findViewById(R.id.detailEdit);
		keyEdit = findViewById(R.id.keyEdit);
		insertBtn.setOnClickListener(v -> {
			String word = wordEdit.getText().toString();
			String detail = wordEdit.getText().toString();
			ContentValues values = new ContentValues();
			values.put(Words.Word.WORD, word);
			values.put(Words.Word.DETAIL, detail);
			getContentResolver().insert(Words.Word.DICT_CONTENT_URI, values);
			Toast.makeText(DictActivity.this, "添加单词成功！", Toast.LENGTH_LONG).show();
		});
		searchBtn.setOnClickListener(v -> {
			String key = keyEdit.getText().toString();
			Cursor cursor = getContentResolver().query(Words.Word.DICT_CONTENT_URI, null, "word like ? or detail like ?", new String[]{"%" + key + "%", "%" + key + "%"}, null);
			Bundle data = new Bundle();
			data.putSerializable("data", converCursorToList(cursor));
			Intent intent = new Intent(DictActivity.this, ResultActivity.class);
			intent.putExtras(data);
			startActivity(intent);
		});
	}

	private ArrayList<Map<String, String>> converCursorToList(Cursor cursor) {
		ArrayList<Map<String, String>> result = new ArrayList<>();
		while (cursor.moveToNext()) {
			Map<String, String> map = new HashMap<>();
			map.put("word", cursor.getString(1));
			map.put("detail", cursor.getString(2));
			result.add(map);
		}
		return result;
	}
}
