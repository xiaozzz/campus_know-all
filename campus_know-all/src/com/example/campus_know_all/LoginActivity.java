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


public class LoginActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login); // ���ø�Activityʹ�õĲ���
		Button button=(Button)findViewById(R.id.login);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Thread(networkTask).start();
				
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

	Handler handler = new Handler() {  
	    @Override  
	    public void handleMessage(Message msg) {  
	        super.handleMessage(msg);  
	        Bundle data = msg.getData();  
	        String val = data.getString("result");  
	        String username = data.getString("username");
	        String nickname = data.getString("nickname");
	        if (val.equals("success")){
	        	Toast.makeText(LoginActivity.this, "��ӭ������"+nickname, Toast.LENGTH_SHORT).show();
				Intent intent=new Intent(LoginActivity.this,MainActivity.class);	//����Ҫ��ʾActivity��Ӧ��Intent����
				Bundle bundle=new Bundle();		//����һ��Bundle�Ķ���bundle
				bundle.putString("id", username);
				bundle.putString("nickname", nickname);	//�����ǳ�
				intent.putExtras(bundle);	//�����ݰ���ӵ�intent������
				startActivity(intent);		//����һ���µ�Activity
				finish();				//����ֱ���˳�	
	        }
	        else{
	        	Toast.makeText(LoginActivity.this, "��½ʧ�ܣ��������û����������룩", Toast.LENGTH_SHORT).show();
	        }
	        // TODO  
	        // UI����ĸ��µ���ز���  
	    }  
	}; 	
	
	Runnable networkTask = new Runnable() {  
		  
	    @Override  
	    public void run() {  
	        // TODO  
	        // ��������� http request.����������ز���  
			String username=((EditText)findViewById(R.id.editText1)).getText().toString();
			String pwd=((EditText)findViewById(R.id.editText2)).getText().toString();
			String nickname="";		//�����ǳƵı���
					
			String target = Data.SERVER + "?type=login";
			target += "&id=" + username;
			target += "&pwd=" + pwd;

			
			String result = "";
			
			try {
				URL url = new URL(target);
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());
				BufferedReader buffer = new BufferedReader(in);
				String inputLine = null;
				/*
				while ((inputLine = buffer.readLine())!=null)
					result += inputLine + "\n";
					*/
				result = buffer.readLine();
				nickname = buffer.readLine();
				in.close();
				urlConnection.disconnect();
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				

			
	        Message msg = new Message();  
	        Bundle data = new Bundle();  
	        data.putString("result", result); 
	        data.putString("username", username);
	        data.putString("nickname", nickname);
	        
	        msg.setData(data);  
	        handler.sendMessage(msg);  
	    }  
	}; 	
	
	
	
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
