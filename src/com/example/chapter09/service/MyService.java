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
	//绑定服务
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
		//service中唯一的抽象方法
		return mBinder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d("MyService", "onCreate executed");
		//变为前台服务的5行代码
		Notification notification = new Notification(R.drawable.ic_launcher,"Notification comes", System.currentTimeMillis());
		Intent notificationIntent = new Intent(this, TestService.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		notification.setLatestEventInfo(this, "这是标题", "这是内容", pendingIntent);
		startForeground(1, notification);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("MyService", "onStartCommand executed");
		//在服务里处理耗时逻辑
		new Thread(new Runnable(){

			@Override
			public void run() {
				//处理具体逻辑
				
				//自行停止
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
