package com.example.mainactivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ToggleSwitchButton extends AppCompatActivity {
	ToggleButton togglebtn;
	Switch switchbtn;
	LinearLayout ll;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toggle_switch);
		togglebtn = findViewById(R.id.togglebtn);
		switchbtn = findViewById(R.id.switchbtn);
		ll = findViewById(R.id.ll);
		CompoundButton.OnCheckedChangeListener listener = (button, isChecked) -> {
			if (isChecked) {
				ll.setOrientation(LinearLayout.VERTICAL);
				togglebtn.setChecked(true);
				switchbtn.setChecked(true);
			} else {
				ll.setOrientation(LinearLayout.HORIZONTAL);
				togglebtn.setChecked(false);
				switchbtn.setChecked(false);
			}
		};
		togglebtn.setOnCheckedChangeListener(listener);
		switchbtn.setOnCheckedChangeListener(listener);
	}
}
