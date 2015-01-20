package com.example.campus_know_all;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;

public class MainActivity extends Activity {
private TabHost tabHost;		//声明TabHost组件的对象
private ListView lv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("TAG","aaaaaa");
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
        Log.v("TAG","bbbbbb");        
        lv = (ListView) findViewById(R.id.listview01);
		SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.listview01,
                new String[]{"info","img"},
                new int[]{R.id.info01,R.id.img01});

		lv.setAdapter(adapter);
		Log.v("TAG","cccccc");		
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Log.v("TAG","1");
				
			}
			
		});        
        
    }	

	private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
 
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("info", "google 1");
        map.put("img", R.drawable.i1);
        list.add(map);
 
        map = new HashMap<String, Object>();
        map.put("info", "google 2");
        map.put("img", R.drawable.i2);
        list.add(map);
 
        map = new HashMap<String, Object>();
        map.put("info", "google 3");
        map.put("img", R.drawable.i3);
        list.add(map);
         
        return list;
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
