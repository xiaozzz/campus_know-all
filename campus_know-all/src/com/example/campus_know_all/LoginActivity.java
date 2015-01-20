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
		setContentView(R.layout.activity_login); // 设置该Activity使用的布局
		Button button=(Button)findViewById(R.id.login);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String username=((EditText)findViewById(R.id.editText1)).getText().toString();
				String pwd=((EditText)findViewById(R.id.editText2)).getText().toString();
				boolean flag=false;	//用于记录登录是否成功的标记变量
				String nickname="";		//保存昵称的变量
				//通过遍历数据的形式判断输入的帐号和密码是否正确
				Log.v("USER.length",Data.USER.length+"");
				for(int i=0;i<Data.USER.length;i++){
					if(username.equals(Data.USER[i][0])){//判断帐号是否正确
						if(pwd.equals(Data.USER[i][1])){	//判断密码是否正确
							nickname=Data.USER[i][2];		//获取昵称
							flag=true;		//将标志变量设置为true
							break;		//跳出for循环
						}
					}
				}
				if(flag){
					Intent intent=new Intent(LoginActivity.this,MainActivity.class);	//创建要显示Activity对应的Intent对象
					Bundle bundle=new Bundle();		//创建一个Bundle的对象bundle
					bundle.putString("id", username);
					bundle.putString("nickname", nickname);	//保存昵称
					intent.putExtras(bundle);	//将数据包添加到intent对象中
					startActivity(intent);		//开启一个新的Activity
					finish();					
				}else{
					Toast.makeText(LoginActivity.this, "您输入的帐号或密码错误！", Toast.LENGTH_SHORT).show();
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
