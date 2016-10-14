package com.itheima.data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private CheckBox cb;
	private EditText et_pwd;
	private EditText et_name;
	private Button bt_login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ��ʼ���ؼ�
		initView();
		// ���ݻ���
		initData();
		// ����ť���õ���¼�
		initClick();
	}

	private void initClick() {
		bt_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ��ȡet������
				String name = et_name.getText().toString().trim();
				String pwd = et_pwd.getText().toString().trim();
				// �ж��Ƿ�Ϊ��
				if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
					Toast.makeText(MainActivity.this, "�û��������벻��Ϊ��",
							Toast.LENGTH_SHORT).show();
					return;
				} else {
					Toast.makeText(MainActivity.this, "��¼�ɹ�",
							Toast.LENGTH_SHORT).show();
				}
				// �ж��Ƿ�ѡcheckbox
				// ��ѡ�򱣴�
				if (cb.isChecked()) {
					try {
						FileOutputStream fos = openFileOutput("info.txt",
								MODE_PRIVATE);
						String content = name + "#" + pwd;
						fos.write(content.getBytes());
						fos.close();

						Toast.makeText(MainActivity.this, "����ɹ�",
								Toast.LENGTH_SHORT).show();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					// ��֮��ɾ��
					boolean deleteFile = deleteFile("info.txt");
					if (deleteFile) {
						Toast.makeText(MainActivity.this, "ɾ���ɹ�",
								Toast.LENGTH_SHORT).show();
					}
				}

			}
		});
	}

	private void initData() {

		try {
			// openFileinput ����ֱ��ָ��data/data/����/files/info.txt
			FileInputStream fis = openFileInput("info.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String content = br.readLine();
			br.close();
			if (!TextUtils.isEmpty(content)) {
				// ����ʱ��#������ ��������ʹ��#�����и�
				String[] split = content.split("#");
				String name = split[0];
				String pwd = split[1];
				et_name.setText(name);
				// ���Կ��ƹ�������λ��
				et_name.setSelection(name.length());

				et_pwd.setText(pwd);

				// ��checkbox����Ϊ��ѡ״̬
				cb.setChecked(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initView() {
		et_name = (EditText) findViewById(R.id.et_name);
		et_pwd = (EditText) findViewById(R.id.et_pwd);
		cb = (CheckBox) findViewById(R.id.cb);
		bt_login = (Button) findViewById(R.id.bt_login);
	}
}
