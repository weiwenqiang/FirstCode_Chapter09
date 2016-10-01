package com.example.chapter09.thread;

import com.example.chapter09.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class TestAndroidThread extends Activity implements OnClickListener {
	private TextView text;
	private Button changeText;

	public static final int UPDATE_TEXT = 1;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE_TEXT:
				// 在这里进行UI操作
				text.setText("Nice to meet you");
				break;

			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b1_androidthread);

		text = (TextView) findViewById(R.id.b1_text);
		changeText = (Button) findViewById(R.id.b1_change_text);
		changeText.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b1_change_text:
			new Thread(new Runnable() {

				@Override
				public void run() {
					Message message = new Message();
					message.what = UPDATE_TEXT;
					// 将Messaged对象发送出去
					handler.sendMessage(message);
				}
			}).start();
			break;
		default:
			break;
		}
	}
}
