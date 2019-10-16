package com.example.mainactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterViewActivity extends AppCompatActivity {
	private String[] names = new String[]{
			"P", "P", "M", "F"
	};
	private String[] descs = new String[]{
			"Panda", "penguin", "mango", "fish"
	};
	private int[] imgs = new int[]{
			R.mipmap.panda, R.mipmap.penguin, R.mipmap.mango, R.mipmap.fish
	};
	ListView listView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adapterview);
		listView = findViewById(R.id.lv);
		List<Map<String, Object>> listItems = new ArrayList<>();
		for (int i = 0; i < names.length; i++) {
			Map<String, Object> listItem = new HashMap<>();
			listItem.put("header", imgs[i]);
			listItem.put("name", names[i]);
			listItem.put("desc", descs[i]);
			listItems.add(listItem);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.simple_item, new String[]{"header", "name", "desc"}, new int[]{R.id.header, R.id.name, R.id.desc});
		listView.setAdapter(simpleAdapter);
		listView.setOnItemClickListener((parent, view, position, id) -> {
			Toast.makeText(this, descs[position], Toast.LENGTH_LONG).show();
		});
	}
}
