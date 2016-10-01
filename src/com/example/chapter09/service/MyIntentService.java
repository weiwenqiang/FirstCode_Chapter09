package com.example.chapter09.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {

	public MyIntentService() {
		//���ø�����вι��캯��
		super("MyIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		//����ʵ��������󷽷������Դ���һЩ������߼������Ҳ��õ���ANR������
		//��ӡ��ǰ�̵߳�ID
		Log.d("MyIntentService", "Thread id is "+ Thread.currentThread().getId());
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d("MyIntentService", "onDestroy executed");
	}

}
