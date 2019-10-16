package com.example.mainactivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ExpandableListViewActivity extends AppCompatActivity {
	private String[] names = new String[]{
			"Panda", "Penguin", "Fish", "Mango"
	};
	private int imgs[] = new int[]{
			R.mipmap.panda, R.mipmap.penguin, R.mipmap.mango, R.mipmap.fish
	};
	private String[] descs = new String[]{
			"a cute panda", "a king penguin", "a tasteful fish", "a mini mango"
	};
	private String[][] names_child = new String[][]{
			new String[]{"p", "a", "n", "d", "a"},
			new String[]{"p", "e", "n", "g", "u", "i", "n"},
			new String[]{"m", "a", "n", "g", "o"},
			new String[]{"f", "i", "s", "h"}
	};
	ExpandableListView expandableListView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expandable_listview);
		BaseExpandableListAdapter baseExpandableListAdapter = new BaseExpandableListAdapter() {
			@Override
			public int getGroupCount() {
				return imgs.length;
			}

			@Override
			public int getChildrenCount(int groupPosition) {
				return names_child[groupPosition].length;
			}

			@Override
			public Object getGroup(int groupPosition) {
				return descs[groupPosition];
			}

			@Override
			public Object getChild(int groupPosition, int childPosition) {
				return names_child[groupPosition][childPosition];
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
//				配置视图
				LinearLayout ll;
				ViewHolder viewHolder;
				if (convertView == null) {
					ll = new LinearLayout(ExpandableListViewActivity.this);
					ll.setOrientation(LinearLayout.HORIZONTAL);
					ImageView imageView = new ImageView(ExpandableListViewActivity.this);
					ll.addView(imageView);
					TextView textView = new TextView(ExpandableListViewActivity.this);
					ll.addView(textView);
					viewHolder = new ViewHolder(imageView, textView);
					ll.setTag(viewHolder);
				} else {
					ll = (LinearLayout) convertView;
					viewHolder = (ViewHolder) ll.getTag();
				}
//				绑定数据
				viewHolder.imageView.setImageResource(imgs[groupPosition]);
				viewHolder.textView.setText(getGroup(groupPosition).toString());
				return ll;
			}

			@Override
			public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//				配置视图
				TextView textView;
				if (convertView == null) {
					textView = this.getTextView();
				} else {
					textView = (TextView) convertView;
				}
//				绑定数据
				textView.setText(getChild(groupPosition, childPosition).toString());
				return textView;
			}

			private TextView getTextView() {
				TextView textView = new TextView(ExpandableListViewActivity.this);
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				textView.setLayoutParams(lp);
				textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
				textView.setPadding(36, 10, 0, 10);
				textView.setTextSize(20f);
				return textView;
			}

			@Override
			public boolean isChildSelectable(int groupPosition, int childPosition) {
				return false;
			}
		};
		expandableListView = findViewById(R.id.elv);
		expandableListView.setAdapter(baseExpandableListAdapter);
	}

	class ViewHolder {
		ImageView imageView;
		TextView textView;

		public ViewHolder(ImageView imageView, TextView textView) {
			this.imageView = imageView;
			this.textView = textView;
		}
	}

}
