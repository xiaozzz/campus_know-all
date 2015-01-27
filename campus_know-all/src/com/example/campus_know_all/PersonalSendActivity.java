package com.example.campus_know_all;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
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
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class PersonalSendActivity extends Activity {
private String username;
private String result;
private String date;
final int CODE = 0x717;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_send);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		username = bundle.getString("username");	
		
		//----------------日期拾取器------------------------
		Button bt = (Button) findViewById(R.id.time_choose);
		bt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(PersonalSendActivity.this,TimeActivity.class);
				startActivityForResult(intent,CODE);
			}
			
		});			
		
		//----------------按钮------------------------
		
		bt = (Button) findViewById(R.id.send_cancel);
		bt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});		
		
		bt = (Button) findViewById(R.id.send_submit);
		bt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// TODO
				
		        Thread t1 = new Thread(networkTask1);
		        t1.start();
		        //Log.v("list_size1",list1.size()+"");
		        try {
					t1.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (result.equals("success"))
				{
					Toast.makeText(PersonalSendActivity.this, "发送成功!", Toast.LENGTH_SHORT).show();
					finish();
				}
				else
					Toast.makeText(PersonalSendActivity.this, "发送失败!", Toast.LENGTH_SHORT).show();				
				
			}
			
		});		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==CODE && resultCode==CODE){
			Log.v("here","here");
			Bundle bundle = data.getExtras();
			Log.v("here","here");
			date = bundle.getString("date");
			Log.v("date",date);
			Button bt = (Button) findViewById(R.id.time_choose);
			bt.setText(date);
		}
	}	
	
	Runnable networkTask1 = new Runnable() {  
		  
	    @Override  
	    public void run() {  
	        // TODO  
	        // 在这里进行 http request.网络请求相关操作  
	    	String target = Data.SERVER + "?type=send_entrust";
	    	target += "&username=" + username;
	    	EditText et;
	    	StringBuilder sb = new StringBuilder();
	    	try {
		    	sb.append("&time=");
		    	sb.append(URLEncoder.encode(date, "UTF-8"));    	
		    	//et = (EditText)findViewById(R.id.send_editText1);
				//sb.append(URLEncoder.encode(et.getText().toString().trim(), "UTF-8"));
		    	
		    	sb.append("&place=");
		    	et = (EditText)findViewById(R.id.send_editText2);
				sb.append(URLEncoder.encode(et.getText().toString().trim(), "UTF-8"));		
				sb.append("&e_type=");
				Spinner spinner = (Spinner) findViewById(R.id.spinner1);
				sb.append(spinner.getSelectedItemPosition()+1);
		    	sb.append("&description=");
		    	et = (EditText)findViewById(R.id.send_editText4);
				sb.append(URLEncoder.encode(et.getText().toString().trim(), "UTF-8"));					
		    	sb.append("&event=");
		    	et = (EditText)findViewById(R.id.send_editText5);
				sb.append(URLEncoder.encode(et.getText().toString().trim(), "UTF-8"));				
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
	    	target += sb.toString();
	    	
			result = "";
			
			Log.v("target",target);
			
			
			
			try {
				URL url = new URL(target);
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());
				BufferedReader buffer = new BufferedReader(in);
				String inputLine = null;
				Map<String, Object> map;
				inputLine = buffer.readLine();
				result = inputLine;
				
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
		getMenuInflater().inflate(R.menu.personal_send, menu);
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
