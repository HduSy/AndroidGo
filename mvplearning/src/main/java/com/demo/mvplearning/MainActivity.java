package com.demo.mvplearning;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MvpView {
	ProgressDialog progressDialog;
	TextView text;
	MvpPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		text = findViewById(R.id.text);
		progressDialog = new ProgressDialog(this);
		progressDialog.setCancelable(false);
		progressDialog.setMessage("正在加载数据");
		presenter = new MvpPresenter(this);
	}

	@Override
	public void showLoading() {
		if (!progressDialog.isShowing()) {
			progressDialog.show();
		}
	}

	@Override
	public void hideLoading() {
		if (progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
	}

	@Override
	public void showData(String data) {
		text.setText(data);
	}

	@Override
	public void showFailureMessage(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
		text.setText(msg);
	}

	@Override
	public void showErrorMessage() {
		Toast.makeText(this, "网络请求数据出现异常", Toast.LENGTH_SHORT).show();
		text.setText("网络请求数据出现异常");
	}

	public void getData(View view){
		presenter.getData("normal");
	}
	public void getDataForFailure(View view){
		presenter.getData("failure");
	}
	public void getDataForError(View view){
		presenter.getData("error");
	}
}
