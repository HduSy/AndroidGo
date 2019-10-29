package com.example.fragmenttest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.model.BookManager;

//todo: 闪退或启动失败
public class ActivityFragmentBook extends Fragment {

	public static final String ITEM_ID = "item_id";
	BookManager.Book book; //保存该Fragment显示的Book对象

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//如果启动该Fragment时包含了ITEM_ID参数
		if (getArguments().containsKey(ITEM_ID)) {
			book = BookManager.ITEM_MAP.get(getArguments().get(ITEM_ID));//①
		}
	}

	//重写该方法，该方法返回的View将作为Fragment显示的组件
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//加载/res/layout/目录下的fragment_book_detail.xml布局文件
		View rootView = inflater.inflate(R.layout.activity_fragment_book, container, false);
		if (book != null) {
			((TextView) rootView.findViewById(R.id.book_title)).setText(book.title);
			((TextView) rootView.findViewById(R.id.book_desc)).setText(book.desc);
		}
		return rootView;
	}
}
