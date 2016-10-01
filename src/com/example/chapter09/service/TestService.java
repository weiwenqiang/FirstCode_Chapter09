package com.example.chapter09.service;

import com.example.chapter09.R;
import com.example.chapter09.service.MyService.DownloadBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class TestService extends Activity implements OnClickListener {
	private MyService.DownloadBinder downloadBinder;
	private ServiceConnection connection = new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			downloadBinder = (DownloadBinder) service;
			downloadBinder.startDownload();
			downloadBinder.getProgress();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b2_service);
		findViewById(R.id.b2_start_service).setOnClickListener(this);
		findViewById(R.id.b2_stop_service).setOnClickListener(this);
		findViewById(R.id.b2_bind_service).setOnClickListener(this);
		findViewById(R.id.b2_unbind_service).setOnClickListener(this);
		findViewById(R.id.b2_intentservice).setOnClickListener(this);
	}
	
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b2_start_service:
			Intent startIntent = new Intent(TestService.this, MyService.class);
			startService(startIntent);//启动服务
			break;
		case R.id.b2_stop_service:
			Intent stopIntent = new Intent(TestService.this, MyService.class);
			stopService(stopIntent);
			break;
		case R.id.b2_bind_service:
			Intent bindIntent = new Intent(TestService.this, MyService.class);
			//绑定服务
			bindService(bindIntent, connection, BIND_AUTO_CREATE);
			break;
		case R.id.b2_unbind_service:
			//解绑服务
			unbindService(connection);
			break;
		case R.id.b2_intentservice:
			//打印主线程ID
			Log.d("TestService", "Thread id is "+ Thread.currentThread().getId());
			Intent intentService = new Intent(this, MyIntentService.class);
			startService(intentService);
			break;
		default:
			break;
		}
	}

}
