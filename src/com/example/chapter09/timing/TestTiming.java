package com.example.chapter09.timing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class TestTiming extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = new Intent(this, LongRunningService.class);
		startService(intent);
	}

}
