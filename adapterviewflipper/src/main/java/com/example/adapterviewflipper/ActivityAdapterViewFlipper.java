package com.example.adapterviewflipper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ActivityAdapterViewFlipper extends AppCompatActivity {
	private int[] imgs = new int[]{R.mipmap.banana, R.mipmap.pineapple, R.mipmap.strawberry};
	private AdapterViewFlipper avf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adapter_view_flipper);
		avf = findViewById(R.id.avf);
		BaseAdapter baseAdapter = new BaseAdapter() {
			@Override
			public int getCount() {
				return imgs.length;
			}

			@Override
			public Object getItem(int position) {
				return position;
			}

			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ImageView imageView;
				if (convertView == null) {
					imageView = new ImageView(ActivityAdapterViewFlipper.this);
				} else {
					imageView = (ImageView) convertView;
				}
				imageView.setImageResource(imgs[position]);
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);
				imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
				return imageView;
			}
		};
		avf.setAdapter(baseAdapter);
	}
	public void prev(View v){
		avf.showPrevious();
		avf.stopFlipping();
	}
	public void next(View v){
		avf.showNext();
		avf.stopFlipping();
	}
	public void auto(View v){
		avf.startFlipping();
	}
}
