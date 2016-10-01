package com.example.chapter09.asynctask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

public class DownloadTask extends AsyncTask<Void, Integer, Boolean> {
/*
 * 如果想启动这个任务，只需编写一下代码
 * new DownloadTask().execute();
 */
	private ProgressDialog progressDialog;

	@Override
	protected void onPreExecute() {
		// 显示进度对话框
		progressDialog.show();
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		//诀窍：在这个方法里去执行具体的耗时任务
		try {
			while (true) {
				// 这里是一个虚构的方法
				int downloadPercent = doDownload();
				publishProgress(downloadPercent);
				if (downloadPercent >= 100) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		//诀窍：在这个方法里进行UI操作
		//在这里更新下载进度
		progressDialog.setMessage("Downloaded "+values[0] + "%");
	}
	

	@Override
	protected void onPostExecute(Boolean result) {
		//诀窍：在这个方法里执行一些任务的收尾工作
		//关闭进度对话框
		progressDialog.dismiss();
		//在这里更新下载结果
		if(result){
			Toast.makeText(null, "Download succeeded(下载成功)", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(null, "Download succeeded(下载失败)", Toast.LENGTH_SHORT).show();
		}
	}

	// 虚构方法，用于计算当前的下载进度并返回
	public int doDownload() {
		return 0;
	}

}
