package com.demo.mvplearning;

/**
 * 定义数据请求的各种反馈状态
 */
public interface MvpCallback {
	void onSuccess(String data);
	void onFailure(String msg);
	void onError();
	void onComplete();
}
