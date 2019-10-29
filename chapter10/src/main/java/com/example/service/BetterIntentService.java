package com.example.service;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

public class BetterIntentService extends IntentService {
	public BetterIntentService() {
		super("BetterIntentService");
	}

	/**
	 * Creates an IntentService.  Invoked by your subclass's constructor.
	 *
	 * @param name Used to name the worker thread, important only for debugging.
	 */
	public BetterIntentService(String name) {
		super(name);
	}

	@Override
	protected void onHandleIntent(@Nullable Intent intent) {
		long endTime = System.currentTimeMillis() + 20 * 1000;
		System.out.println("onHandleIntent");
		while (System.currentTimeMillis() < endTime) {
			synchronized (this) {
				try {
					wait(endTime - System.currentTimeMillis());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("耗时任务执行完成");
	}
}
