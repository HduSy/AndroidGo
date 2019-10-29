package com.example.viewswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;
//TODO: 启动不了闪退
public class ActivityViewSwitcher extends AppCompatActivity {
	public static final int NUMBER_PER_SCREEN = 12;
	private int screenNo = -1;
	private int screenCount = 0;
	private List<DataItem> items = new ArrayList<>();
	private ViewSwitcher vs;
	private LayoutInflater inflater;
	private BaseAdapter baseAdapter = new BaseAdapter() {
		@Override
		public int getCount() {
			if (screenNo == screenCount - 1 && items.size() % NUMBER_PER_SCREEN != 0) {
				return items.size() % NUMBER_PER_SCREEN;
			} else {
				return NUMBER_PER_SCREEN;
			}
		}

		@Override
		public DataItem getItem(int position) {
			return items.get(screenNo * NUMBER_PER_SCREEN + position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			ViewHolder viewHolder;
			if (convertView == null) {
				view = inflater.inflate(R.layout.labelicon, null);
				ImageView imageView = findViewById(R.id.img);
				TextView textView = findViewById(R.id.tv);
				viewHolder = new ViewHolder(imageView, textView);
				view.setTag(viewHolder);
			} else {
				view = convertView;
				viewHolder = (ViewHolder) view.getTag();
			}
			viewHolder.imageView.setImageDrawable(getItem(position).drawable);
			viewHolder.textView.setText(getItem(position).dataName);
			return view;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_switcher);
		inflater = LayoutInflater.from(this);
		for (int i = 0; i < 40; i++) {
			String label = i + "";
			Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher, null);
			DataItem item = new DataItem(label, drawable);
			items.add(item);
		}
		screenCount = items.size() % NUMBER_PER_SCREEN == 0 ? items.size() / NUMBER_PER_SCREEN : items.size() / NUMBER_PER_SCREEN + 1;
		vs = findViewById(R.id.vs);
		vs.setFactory(() -> inflater.inflate(R.layout.slidelistview, null));
		next(null);
	}

	public static class DataItem {
		String dataName;
		Drawable drawable;

		DataItem(String dataName, Drawable drawable) {
			this.dataName = dataName;
			this.drawable = drawable;
		}
	}

	public static class ViewHolder {
		ImageView imageView;
		TextView textView;

		ViewHolder(ImageView imageView, TextView t) {
			this.imageView = imageView;
			this.textView = t;
		}
	}

	public void next(View v) {
		if (screenNo < screenCount - 1) {
			screenNo++;
			vs.setInAnimation(this, R.anim.slide_in_right);
			vs.setOutAnimation(this, R.anim.slide_out_left);
			((GridView) vs.getNextView()).setAdapter(baseAdapter);
			vs.showNext();
		}
	}

	public void prev(View v) {
		if (screenNo > 0) {
			screenNo--;
			vs.setInAnimation(this, R.anim.slide_out_left);
			vs.setOutAnimation(this, R.anim.slide_in_right);
			((GridView) vs.getNextView()).setAdapter(baseAdapter);
			vs.showPrevious();
		}
	}
}
