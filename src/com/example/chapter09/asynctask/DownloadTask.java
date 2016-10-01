package com.example.chapter09.asynctask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

public class DownloadTask extends AsyncTask<Void, Integer, Boolean> {
/*
 * ����������������ֻ���дһ�´���
 * new DownloadTask().execute();
 */
	private ProgressDialog progressDialog;

	@Override
	protected void onPreExecute() {
		// ��ʾ���ȶԻ���
		progressDialog.show();
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		//���ϣ������������ȥִ�о���ĺ�ʱ����
		try {
			while (true) {
				// ������һ���鹹�ķ���
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
		//���ϣ���������������UI����
		//������������ؽ���
		progressDialog.setMessage("Downloaded "+values[0] + "%");
	}
	

	@Override
	protected void onPostExecute(Boolean result) {
		//���ϣ������������ִ��һЩ�������β����
		//�رս��ȶԻ���
		progressDialog.dismiss();
		//������������ؽ��
		if(result){
			Toast.makeText(null, "Download succeeded(���سɹ�)", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(null, "Download succeeded(����ʧ��)", Toast.LENGTH_SHORT).show();
		}
	}

	// �鹹���������ڼ��㵱ǰ�����ؽ��Ȳ�����
	public int doDownload() {
		return 0;
	}

}
