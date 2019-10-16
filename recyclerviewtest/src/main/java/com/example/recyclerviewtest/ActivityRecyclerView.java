package com.example.recyclerviewtest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ActivityRecyclerView extends AppCompatActivity {
	private RecyclerView recyclerView;
	private List<Person> personList = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recycler_view);
		recyclerView = findViewById(R.id.recycler);
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(linearLayoutManager);
		initData();
		RecyclerView.Adapter adapter = new RecyclerView.Adapter<PersonViewHolder>() {
			@NonNull
			@Override
			public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
				View view = LayoutInflater.from(ActivityRecyclerView.this).inflate(R.layout.simple_item, null);
				return new PersonViewHolder(view, this);
			}

			@Override
			public void onBindViewHolder(@NonNull PersonViewHolder personViewHolder, int position) {
				personViewHolder.nameTv.setText(personList.get(position).name);
				personViewHolder.descTv.setText(personList.get(position).desc);
				personViewHolder.iv.setImageResource(personList.get(position).header);
			}

			@Override
			public int getItemCount() {
				return personList.size();
			}
		};
		recyclerView.setAdapter(adapter);
	}

	private void initData() {
		String[] names = new String[]{
				"Panda", "Penguin", "Fish"
		};
		String[] descs = new String[]{
				"a cute panda", "a black penguin", "a tasteful fish"
		};
		int[] headers = new int[]{R.mipmap.panda, R.mipmap.penguin, R.mipmap.fish};
		for (int i = 0; i < names.length; i++) {
			personList.add(new Person(headers[i], names[i], descs[i]));
		}
	}

	class PersonViewHolder extends RecyclerView.ViewHolder {
		View rootView;
		TextView nameTv, descTv;
		ImageView iv;
		private RecyclerView.Adapter adapter;

		/**
		 * layout,adapter
		 * can be self-defined here
		 *
		 * @param itemView
		 * @param adapter
		 */
		public PersonViewHolder(@NonNull View itemView, RecyclerView.Adapter adapter) {
			super(itemView);
			this.nameTv = itemView.findViewById(R.id.name);
			this.descTv = itemView.findViewById(R.id.desc);
			this.iv = itemView.findViewById(R.id.header);
			this.rootView = itemView.findViewById(R.id.rootview);
			this.adapter = adapter;
		}
	}

	class Person {
		private int header;
		private String name;
		private String desc;

		Person(int header, String name, String desc) {
			this.header = header;
			this.name = name;
			this.desc = desc;
		}
	}
}
