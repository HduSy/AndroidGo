package com.example.expandablelistviewtest;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ExpandableListViewActivity extends AppCompatActivity {
	private int[] imgs = new int[]{R.mipmap.panda, R.mipmap.penguin, R.mipmap.fish, R.mipmap.mango};
	private String[] names = new String[]{
			"Panda", "Penguin", "Fish", "Mango"
	};
	private String[][] descs = new String[][]{
			new String[]{"p", "a", "n", "d", "a"},
			new String[]{"p", "e", "n", "g", "u", "i", "n"},
			new String[]{"m", "a", "n", "g", "o"},
			new String[]{"f", "i", "s", "h"}
	};
	ExpandableListView expandableListView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expandablelistview);
		BaseExpandableListAdapter baseExpandableListAdapter = new BaseExpandableListAdapter() {
			@Override
			public int getGroupCount() {
				return names.length;
			}

			@Override
			public int getChildrenCount(int groupPosition) {
				return descs.length;
			}

			//			拿到后面需调用到的父级数据
			@Override
			public Object getGroup(int groupPosition) {
				return names[groupPosition];
			}

			//          拿到后面需要调用的子级数据
			@Override
			public Object getChild(int groupPosition, int childPosition) {
				return descs[groupPosition][childPosition];
			}

			@Override
			public long getGroupId(int groupPosition) {
				return groupPosition;
			}

			@Override
			public long getChildId(int groupPosition, int childPosition) {
				return childPosition;
			}

			@Override
			public boolean hasStableIds() {
				return true;
			}

			@Override
			public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//				设计视图
				LinearLayout ll;
				ViewHolder vh;
				if (convertView == null) {
					ll = new LinearLayout(ExpandableListViewActivity.this);
					ll.setOrientation(LinearLayout.HORIZONTAL);
					ImageView iv = new ImageView(ExpandableListViewActivity.this);
					ll.addView(iv);
					TextView tv = new TextView(ExpandableListViewActivity.this);
					ll.addView(tv);
					vh = new ViewHolder(iv, tv);
					ll.setTag(vh); //!important
				} else {
//					无缓存
					ll = (LinearLayout) convertView;
					vh = (ViewHolder) ll.getTag();
				}
//				绑定数据
				vh.imageView.setImageResource(imgs[groupPosition]);
				vh.textView.setText(getGroup(groupPosition).toString());
				return ll;
			}

			@Override
			public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//				设计视图
				TextView tv;
				if (convertView == null) {
//					tv = new TextView(ExpandableListViewActivity.this);
					tv = getTextView();
				} else {
					tv = (TextView) convertView;
				}
				tv.setText(getChild(groupPosition, childPosition).toString());
				return tv;
			}

			@Override
			public boolean isChildSelectable(int groupPosition, int childPosition) {
				return true;
			}

			public TextView getTextView() {
				TextView tv = new TextView(ExpandableListViewActivity.this);
				tv.setTextSize(20f);
				tv.setPadding(36, 10, 0, 10);
				tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.START); //!forget
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				return tv;
			}
		};
		expandableListView = findViewById(R.id.elv);
		expandableListView.setAdapter(baseExpandableListAdapter);
	}

	//  若getChildView或getGroupView方法的convertView组件内包含多个需要设置显示数据的子组件，为了高效访问这些子组件，
	//  程序该提供一个ViewHolder来管理这些子组件
	class ViewHolder {
		ImageView imageView;
		TextView textView;

		public ViewHolder(ImageView imageView, TextView textView) {
			this.imageView = imageView;
			this.textView = textView;
		}
	}
}
