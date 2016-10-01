package com.example.chapter09.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {

	public MyIntentService() {
		//调用父类的有参构造函数
		super("MyIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		//子类实现这个抽象方法，可以处理一些具体的逻辑，而且不用担心ANR的问题
		//打印当前线程的ID
		Log.d("MyIntentService", "Thread id is "+ Thread.currentThread().getId());
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d("MyIntentService", "onDestroy executed");
	}

}
