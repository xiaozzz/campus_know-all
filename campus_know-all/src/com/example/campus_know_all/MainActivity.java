package com.example.campus_know_all;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
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

public class MainActivity extends Activity {
private TabHost tabHost;		//声明TabHost组件的对象
private ListView lv;
private ListView lv2;
private String username="";
private String nickname="";
private int entrust_type = 0; 		//0表示全部，1表示只看自己
private List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();			//school_act_list
private Vector vi = new Vector(100);

private List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();			//entrust_list
private Vector vi2 = new Vector(100);
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        username = bundle.getString("username");
        nickname = bundle.getString("nickname");      

        tabHost=(TabHost)findViewById(android.R.id.tabhost);	//获取TabHost对象
        tabHost.setup();	//初始化TabHost组件
        LayoutInflater inflater = LayoutInflater.from(this); 	// 声明并实例化一个LayoutInflater对象  
        inflater.inflate(R.layout.tab1, tabHost.getTabContentView());  
        inflater.inflate(R.layout.tab2, tabHost.getTabContentView());
        tabHost.addTab(tabHost.newTabSpec("tab01")
        		.setIndicator("校园活动")
        		.setContent(R.id.LinearLayout01));   //添加第一个标签页
        tabHost.addTab(tabHost.newTabSpec("tab02")
        		.setIndicator("个人委托")
        		.setContent(R.id.LinearLayout02));  	//添加第二个标签页       
        lv = (ListView) findViewById(R.id.listview01);
		SimpleAdapter adapter = null;

		adapter = new SimpleAdapter(this,getData(),R.layout.listview01,
			        new String[]{"info","img"},
			        new int[]{R.id.info01,R.id.img01});


		lv.setAdapter(adapter);	
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(MainActivity.this,SchoolActivity.class);
				Bundle bundle = new Bundle();

				//int tmp = position; //tmp = pos2id(position);转化为消息的primary key
				String tmp = vi.get(position).toString();
				
				bundle.putString("id", tmp);
				bundle.putString("username", username);
				intent.putExtras(bundle);
							
				//给activity的信息有当前用户名，新闻ID
				startActivity(intent);

			}
			
		});        
 
        lv2 = (ListView) findViewById(R.id.listview02);
		SimpleAdapter adapter2 = new SimpleAdapter(this,getData2(),R.layout.listview02,
                new String[]{"info","img"},
                new int[]{R.id.info02,R.id.img02});

		lv2.setAdapter(adapter2);	
		
		lv2.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (entrust_type == 0)
				{
					Intent intent = new Intent(MainActivity.this,PersonalActivity.class);
					Bundle bundle = new Bundle();
	
					String tmp = vi2.get(position).toString();
					
					bundle.putString("id", tmp);
					bundle.putString("username", username);
					intent.putExtras(bundle);
					//给activity的信息有当前用户名，新闻ID
					startActivity(intent);
				}
				else
				{
					//TODO
				}
			}
			
		});     		
		
		Button bt = (Button)findViewById(R.id.chakan_1);
		bt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				entrust_type = 0;
				vi2.clear();
				set_data2();
			}
		});
		bt = (Button)findViewById(R.id.chakan_2);
		bt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				entrust_type = 1;
				vi2.clear();
				set_data2();
			}
		});		
    }	

	private List<Map<String, Object>> getData() {
        //List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        
        Thread t1 = new Thread(networkTask1);
        list1.clear();
        t1.start();
        //Log.v("list_size1",list1.size()+"");
        try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //Log.v("list_size2",list1.size()+"");
        
        return list1;
    }    
 		
	Runnable networkTask1 = new Runnable() {  
		  
	    @Override  
	    public void run() {  
	        // TODO  
	        // 在这里进行 http request.网络请求相关操作  
		String target = Data.SERVER + "?type=school_act_list";
		
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
			        map.put("id", inputLine);	        
			        vi.add(inputLine);
			        
					if ((inputLine = buffer.readLine())==null)
						break;
			        if (inputLine.equals("理工"))
			        	map.put("img", R.drawable.li);
			        else if (inputLine.equals("文学"))
				        	map.put("img", R.drawable.wen);
			        else if (inputLine.equals("体育"))
			        	map.put("img", R.drawable.ti);
			        else map.put("img", R.drawable.qi);
			        
					if ((inputLine = buffer.readLine())==null)
						break;
			        map.put("info", inputLine);
			        
		  
			        list1.add(map);					
				
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
	
	
	
	private List<Map<String, Object>> getData2() {
        //List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        
        Thread t2 = new Thread(networkTask2);
        list2.clear();
        t2.start();
        //Log.v("list_size1",list1.size()+"");
        try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //Log.v("list_size2",list1.size()+"");
        
        return list2;
    }    
 		
	Runnable networkTask2 = new Runnable() {  
		  
	    @Override  
	    public void run() {  
	        // TODO  
	        // 在这里进行 http request.网络请求相关操作  
	    	String target = Data.SERVER + "?type=entrust_list";
	    	target += "&entrust_type=" + entrust_type;
	    	target += "&username=" + username;
	    	
	    	
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
			        map.put("id", inputLine);	        
			        vi2.add(inputLine);
			        
					if ((inputLine = buffer.readLine())==null)
						break;
			        if (inputLine.equals("朝遗夕拾"))
			        	map.put("img", R.drawable.zhaoyixishi);
			        else if (inputLine.equals("题目解答"))
				        	map.put("img", R.drawable.timujieda);
			        else if (inputLine.equals("找人合作"))
			        	map.put("img", R.drawable.zhaorenhezuo);
			        else map.put("img", R.drawable.qita);
			        
					if ((inputLine = buffer.readLine())==null)
						break;
			        map.put("info", inputLine);
			        
		  
			        list2.add(map);					
				
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

	private void set_data2(){
        lv2 = (ListView) findViewById(R.id.listview02);
		SimpleAdapter adapter2 = new SimpleAdapter(this,getData2(),R.layout.listview02,
                new String[]{"info","img"},
                new int[]{R.id.info02,R.id.img02});

		lv2.setAdapter(adapter2);		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
