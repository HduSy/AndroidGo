package com.demo.mvplearning;

/**
 * 根据业务逻辑 提供调用Activity中具体UI逻辑操作方法
 */
public interface MvpView {
	void showLoading();

	void hideLoading();

	/**
	 * 数据请求成功后调用此接口显示数据
	 *
	 * @param data
	 */
	void showData(String data);

	void showFailureMessage(String msg);

	void showErrorMessage();
}
