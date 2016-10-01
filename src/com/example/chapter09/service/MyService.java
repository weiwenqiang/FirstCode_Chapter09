package com.example.chapter09.service;

import com.example.chapter09.R;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
	//�󶨷���
	private DownloadBinder mBinder = new DownloadBinder();
	
	class DownloadBinder extends Binder{
		
		public void startDownload(){
			Log.d("MyService", "startDownload executed");
		}
		
		public int getProgress(){
			Log.d("MyService", "getProgress executed");
			return 0;
		}
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		//service��Ψһ�ĳ��󷽷�
		return mBinder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d("MyService", "onCreate executed");
		//��Ϊǰ̨�����5�д���
		Notification notification = new Notification(R.drawable.ic_launcher,"Notification comes", System.currentTimeMillis());
		Intent notificationIntent = new Intent(this, TestService.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		notification.setLatestEventInfo(this, "���Ǳ���", "��������", pendingIntent);
		startForeground(1, notification);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("MyService", "onStartCommand executed");
		//�ڷ����ﴦ���ʱ�߼�
		new Thread(new Runnable(){

			@Override
			public void run() {
				//��������߼�
				
				//����ֹͣ
				stopSelf();
			}
		}).start();;
		
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Log.d("MyService", "onDestroy executed");
		super.onDestroy();
	}

}
