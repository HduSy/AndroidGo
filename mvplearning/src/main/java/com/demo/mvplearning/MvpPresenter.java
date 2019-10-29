package com.demo.mvplearning;

/**
 * 具体的逻辑业务处理类，该类为纯Java类，不包含任何Android API
 * 负责请求数据，并对数据请求的反馈进行处理
 */
public class MvpPresenter {
	private MvpView mView;

	public MvpPresenter(MvpView view) {
		this.mView = view;
	}

	public void getData(String params) {
		mView.showLoading();
		MvpModel.getNetData(params, new MvpCallback() {
			@Override
			public void onSuccess(String data) {
				mView.showData(data);
			}

			@Override
			public void onFailure(String msg) {
				mView.showFailureMessage(msg);
			}

			@Override
			public void onError() {
				mView.showErrorMessage();
			}

			@Override
			public void onComplete() {
				mView.hideLoading();
			}
		});
	}
}
