package com.example.fragmenttest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ActivityViewPager extends FragmentActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_pager);
		ViewPager viewPager = findViewById(R.id.container);
		SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(pagerAdapter);
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(@NonNull FragmentManager fm) {
			super(fm);
		}

		@NonNull
		@Override
		public Fragment getItem(int position) {
			return DummyFragment.newInstance(position + 1);
		}

		@Override
		public int getCount() {
			return 3;
		}
	}

	//	定义一个fragment
	public static class DummyFragment extends Fragment {
		private static final String ARG_SECTION_NUMBER = "section_number";

		public static DummyFragment newInstance(int sectionNumber) {
			DummyFragment fragment = new DummyFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		@Nullable
		@Override
		public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			TextView textView = rootView.findViewById(R.id.section_label);
			textView.setText("Fragment页面" + getArguments().getInt(ARG_SECTION_NUMBER, 0));
			return rootView;
		}
	}
}
