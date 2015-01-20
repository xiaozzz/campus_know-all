package com.example.campus_know_all;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login); // ���ø�Activityʹ�õĲ���
		Button button=(Button)findViewById(R.id.login);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String username=((EditText)findViewById(R.id.editText1)).getText().toString();
				String pwd=((EditText)findViewById(R.id.editText2)).getText().toString();
				boolean flag=false;	//���ڼ�¼��¼�Ƿ�ɹ��ı�Ǳ���
				String nickname="";		//�����ǳƵı���
				//ͨ���������ݵ���ʽ�ж�������ʺź������Ƿ���ȷ
				Log.v("USER.length",Data.USER.length+"");
				for(int i=0;i<Data.USER.length;i++){
					if(username.equals(Data.USER[i][0])){//�ж��ʺ��Ƿ���ȷ
						if(pwd.equals(Data.USER[i][1])){	//�ж������Ƿ���ȷ
							nickname=Data.USER[i][2];		//��ȡ�ǳ�
							flag=true;		//����־��������Ϊtrue
							break;		//����forѭ��
						}
					}
				}
				if(flag){
					Intent intent=new Intent(LoginActivity.this,MainActivity.class);	//����Ҫ��ʾActivity��Ӧ��Intent����
					Bundle bundle=new Bundle();		//����һ��Bundle�Ķ���bundle
					bundle.putString("id", username);
					bundle.putString("nickname", nickname);	//�����ǳ�
					intent.putExtras(bundle);	//�����ݰ���ӵ�intent������
					startActivity(intent);		//����һ���µ�Activity
					finish();					
				}else{
					Toast.makeText(LoginActivity.this, "��������ʺŻ��������", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		Button register=(Button)findViewById(R.id.register);
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
				startActivity(intent);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
