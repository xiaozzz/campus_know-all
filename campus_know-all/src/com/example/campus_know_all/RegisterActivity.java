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

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		Button button=(Button)findViewById(R.id.reg_register);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String username=((EditText)findViewById(R.id.reg_editText1)).getText().toString();
				String pwd=((EditText)findViewById(R.id.reg_editText2)).getText().toString();
				String pwd2=((EditText)findViewById(R.id.reg_editText3)).getText().toString();
				boolean flag=true;	//���ڼ�¼ע���Ƿ�ɹ��ı�Ǳ���
				String nickname=((EditText)findViewById(R.id.reg_editText4)).getText().toString();
				//ͨ���������ݵ���ʽ�ж�������ʺź������Ƿ���ȷ
				if (!pwd.equals(pwd2))
					flag = false;
				
				for(int i=0;i<Data.USER.length;i++){
					if(username.equals(Data.USER[i][0])){//�ж��ʺ��Ƿ���ȷ
						flag = false;
						break;
					}
				}
				if(flag){
					//String[] str = {username,pwd,nickname};
					//Data.append(str);
					finish();					
				}else{
					Toast.makeText(RegisterActivity.this, "��������ʺ��Ѵ��ڻ������벻һ�£�", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
		Button exit=(Button)findViewById(R.id.reg_exit);
		exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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
