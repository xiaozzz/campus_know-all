package com.example.campus_know_all;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
private TabHost tabHost;		//声明TabHost组件的对象
private ListView lv;
private ListView lv2;
private String username;
private String nickname;
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
		SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.listview01,
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

				int tmp = position; //tmp = pos2id(position);转化为消息的primary key
				bundle.putInt("pos", tmp);
				bundle.putString("username", username);
				intent.putExtras(bundle);
				//给activity的信息有当前用户名，新闻ID
				startActivity(intent);
			}
			
		});        
 
        lv2 = (ListView) findViewById(R.id.listview02);
		SimpleAdapter adapter2 = new SimpleAdapter(this,getData2(),R.layout.listview02,
                new String[]{"info","img"},
                new int[]{R.id.info01,R.id.img01});

		lv2.setAdapter(adapter2);	
		
		lv2.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,PersonalActivity.class);
				Bundle bundle = new Bundle();

				int tmp = position; //tmp = pos2id(position);转化为消息的primary key
				bundle.putInt("pos", tmp);
				bundle.putString("username", username);
				intent.putExtras(bundle);
				//给activity的信息有当前用户名，新闻ID
				startActivity(intent);
			}
			
		});     		
		
    }	

	private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
 
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("info", "王师傅当场证明P!=NP");
        map.put("img", R.drawable.li);
        list.add(map);
 
        map = new HashMap<String, Object>();
        map.put("info", "校篮球打铁大赛");
        map.put("img", R.drawable.ti);
        list.add(map);
 
        map = new HashMap<String, Object>();
        map.put("info", "第一节校文化节吹B大会");
        map.put("img", R.drawable.wen);
        list.add(map);
         
        return list;
    }    
 
	private List<Map<String, Object>> getData2() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
 
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("info", "寻物启事：丢失海南狗一只");
        map.put("img", R.drawable.zhaoyixishi);
        list.add(map);
 
        map = new HashMap<String, Object>();
        map.put("info", "求高人做本软件界面设计！");
        map.put("img", R.drawable.timujieda);
        list.add(map);
 
        map = new HashMap<String, Object>();
        map.put("info", "找人陪我打dota");
        map.put("img", R.drawable.zhaorenhezuo);
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
