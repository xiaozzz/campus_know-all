package com.example.campus_know_all;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
private boolean flag=true;	//用于记录注册是否成功的标记变量
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		Button button=(Button)findViewById(R.id.reg_register);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Thread(networkTask).start();	
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

	Handler handler = new Handler() {  
	    @Override  
	    public void handleMessage(Message msg) {  
	        super.handleMessage(msg);  
	        Bundle data = msg.getData();  
	        String val = data.getString("result");  
	        if (val.equals("success")){
	        	Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
	        	finish();
	        }
	        else{
	        	Toast.makeText(RegisterActivity.this, "注册失败！（已存在该用户或者密码错误）", Toast.LENGTH_SHORT).show();
	        }
	        // TODO  
	        // UI界面的更新等相关操作  
	    }  
	}; 	
	
	Runnable networkTask = new Runnable() {  
		  
	    @Override  
	    public void run() {  
	        // TODO  
	        // 在这里进行 http request.网络请求相关操作  
			String username=((EditText)findViewById(R.id.reg_editText1)).getText().toString();
			String pwd=((EditText)findViewById(R.id.reg_editText2)).getText().toString();
			String pwd2=((EditText)findViewById(R.id.reg_editText3)).getText().toString();			
			String nickname=((EditText)findViewById(R.id.reg_editText4)).getText().toString();
			//通过遍历数据的形式判断输入的帐号和密码是否正确
			if (!pwd.equals(pwd2))
				flag = false;
					
			String target = Data.SERVER + "?type=register";
			target += "&id=" + username;
			target += "&pwd=" + pwd;
			target += "&nickname=" + nickname;
						
			String result = "";
			
			if (flag)
			try {
				URL url = new URL(target);
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());
				BufferedReader buffer = new BufferedReader(in);
				String inputLine = null;
				while ((inputLine = buffer.readLine())!=null)
					result += inputLine + "\n";
				in.close();
				urlConnection.disconnect();
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			result = result.trim();
					
	        Message msg = new Message();  
	        Bundle data = new Bundle();  
	        data.putString("result", result);  
	        
	        msg.setData(data);  
	        handler.sendMessage(msg);  
	    }  
	}; 	
	
	
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
