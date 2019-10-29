package com.example.preferenceactivitytest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class PreferenceActivityTest extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (hasHeaders()) {
			Button button = new Button(this);
			button.setText("设置操作");
			setListFooter(button);
		}
	}

	//	重载负责加载选项设置头布局文件
	@Override
	public void onBuildHeaders(List<Header> target) {
		loadHeadersFromResource(R.xml.preference_headers, target);
	}
//	重载验证各PreferenceFragment是够有效


	@Override
	protected boolean isValidFragment(String fragmentName) {
		return true;
	}

	//	负责加载选项设置布局文件
	public static class Prefs1Fragment extends PreferenceFragment {
		@Override
		public void onCreate(@Nullable Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
//			todo: preferences.xml
			addPreferencesFromResource(R.xml.preferences);
		}
	}

	public static class Prefs2Fragment extends PreferenceFragment {
		@Override
		public void onCreate(@Nullable Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
//			todo: display_prefs
			addPreferencesFromResource(R.xml.display_prefs);
			String website = getArguments().getString("website");
			Toast.makeText(getActivity(), "网址是：" + website, Toast.LENGTH_LONG).show();
		}
	}
}
