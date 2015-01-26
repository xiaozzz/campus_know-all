package com.example.campus_know_all;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SchoolActivity extends Activity {
TextView tv;
Button bt;
String username;
String id = "";
String time = "";
String place = "";
String type = "";
String event = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_school);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		id = bundle.getString("id");
		username = bundle.getString("username");
		//处理id，从服务器端拿出 时间(time)地点(place)类别(type)事件(event)
		//Handle_SchoolActivity(id);
			
        Thread t1 = new Thread(networkTask1);
        t1.start();
        //Log.v("list_size1",list1.size()+"");
        try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		tv = (TextView)findViewById(R.id.editText1);
		tv.setText(time);
		tv = (TextView)findViewById(R.id.editText2);
		tv.setText(place);
		tv = (TextView)findViewById(R.id.editText3);
		tv.setText(type);
		tv = (TextView)findViewById(R.id.editText4);
		tv.setText(event);
		bt = (Button)findViewById(R.id.participate);
		bt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bt.setText("报名成功！");
				bt.setClickable(false);
			}
			
		});
			

	}

	
	Runnable networkTask1 = new Runnable() {  
		  
	    @Override  
	    public void run() {  
	        // TODO  
	        // 在这里进行 http request.网络请求相关操作  
	    	String target = Data.SERVER + "?type=school_act_detail";
	    	target += "&id=" + id;
		
			String result = "";
			
			try {
				URL url = new URL(target);
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());
				BufferedReader buffer = new BufferedReader(in);
				String inputLine = null;
				Map<String, Object> map;
				inputLine = buffer.readLine();
				type = inputLine;
				inputLine = buffer.readLine();
				time = inputLine;				
				inputLine = buffer.readLine();
				place = inputLine;
				inputLine = buffer.readLine();
				event = inputLine;
				
				in.close();
				urlConnection.disconnect();
			
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
	    }  
	};	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.school, menu);
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
