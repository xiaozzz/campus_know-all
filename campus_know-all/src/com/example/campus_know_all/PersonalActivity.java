package com.example.campus_know_all;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalActivity extends Activity {
private TextView tv;
private Button bt;
private String username;
private ListView lv;
private String id;

private String en_id;
private String from_id;
private String content;
private String result;

String time = "";
String place = "";
String type = "";
String person = "";
String event = "";
List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		//reply_List
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		id = bundle.getString("id");
		username = bundle.getString("username");
		
		//----------------------以下为委托内容------------------------------		
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
		tv = (TextView)findViewById(R.id.per_editText1);
		tv.setText(time);
		tv = (TextView)findViewById(R.id.per_editText2);
		tv.setText(place);
		tv = (TextView)findViewById(R.id.per_editText3);
		tv.setText(type);
		tv = (TextView)findViewById(R.id.per_editText4);
		tv.setText(person);
		tv = (TextView)findViewById(R.id.per_editText5);
		tv.setText(event);
		
		//----------------------以下为回复------------------------------
		
        lv = (ListView) findViewById(R.id.per_listview01);
		SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.per_listview01,
                new String[]{"content","nickname"},
                new int[]{R.id.per_content,R.id.per_nickname});

		lv.setAdapter(adapter);	
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
			}
			
		});  
		
		//----------------------以下为回复------------------------------
		Button bt = (Button) findViewById(R.id.per_cancel);
		bt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
		
		bt = (Button) findViewById(R.id.per_submit);
		bt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// TODO
				EditText ev = (EditText)findViewById(R.id.per_input);
				en_id = id;
				from_id = username;
				content = ev.getText().toString().trim(); 

				
		        Thread t3 = new Thread(networkTask3);
		        t3.start();
		        //Log.v("list_size1",list1.size()+"");
		        try {
					t3.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}					
				
				if (result.equals("success"))
					Toast.makeText(PersonalActivity.this, "发送成功!", Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(PersonalActivity.this, "发送失败!", Toast.LENGTH_SHORT).show();
				//refresh_this_activity
				refresh_reply();
			}
			
		});
		
	}

	private List<Map<String, Object>> getData() {
        Thread t1 = new Thread(networkTask2);
        list.clear();
        t1.start();
        //Log.v("list_size1",list1.size()+"");
        try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return list;
    }	

	Runnable networkTask1 = new Runnable() {  
		  
	    @Override  
	    public void run() {  
	        // TODO  
	        // 在这里进行 http request.网络请求相关操作  
	    	String target = Data.SERVER + "?type=entrust_detail";
	    	target += "&id=" + id;
		
			String result = "";
			
			//Log.v("target",target);
			
			try {
				URL url = new URL(target);
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());
				BufferedReader buffer = new BufferedReader(in);
				String inputLine = null;
				Map<String, Object> map;
				inputLine = buffer.readLine();
				time = inputLine;
				inputLine = buffer.readLine();
				place = inputLine;				
				inputLine = buffer.readLine();
				type = inputLine;
				inputLine = buffer.readLine();
				person = inputLine;
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

	Runnable networkTask2 = new Runnable() {  
		  
	    @Override  
	    public void run() {  
	        // TODO  
	        // 在这里进行 http request.网络请求相关操作  
	    	String target = Data.SERVER + "?type=get_reply";
	    	target += "&id=" + id;
		
			String result = "";
			
			try {
				URL url = new URL(target);
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());
				BufferedReader buffer = new BufferedReader(in);
				String inputLine = null;
				Map<String, Object> map;
				while ((inputLine = buffer.readLine())!=null)
				{
			        map = new HashMap<String, Object>();
			        map.put("nickname", inputLine);	        
			        
					if ((inputLine = buffer.readLine())==null)
						break;
			        map.put("content", inputLine);
			        		  
			        list.add(map);					
				
				}
			
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

	Runnable networkTask3 = new Runnable() {  
		  
	    @Override  
	    public void run() {  
	        // TODO  
	        // 在这里进行 http request.网络请求相关操作  
	    	String target = Data.SERVER + "?type=send_reply";
	    	target += "&en_id=" + en_id;
	    	target += "&from_id=" + from_id;
	    	//target += "&content=" + content;
	    	StringBuilder sb = new StringBuilder();
	    	sb.append("&content=");
	    	try {
				sb.append(URLEncoder.encode(content, "UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
	    	target += sb.toString();
	    	
			result = "";
			
			//Log.v("target",target);
			
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
	
	private void refresh_reply(){
        lv = (ListView) findViewById(R.id.per_listview01);
		SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.per_listview01,
                new String[]{"content","nickname"},
                new int[]{R.id.per_content,R.id.per_nickname});

		lv.setAdapter(adapter);		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.personal, menu);
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
