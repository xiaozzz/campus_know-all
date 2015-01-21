package com.example.campus_know_all;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		int id = bundle.getInt("pos");
		username = bundle.getString("username");
		
		//----------------------以下为委托内容------------------------------		
		//处理id，从服务器端拿出 时间(time)地点(place)类别(type)事件(event)
		//Handle_SchoolActivity(id);
		String time = "2015年1月1日~2015年2月1日";
		String place = "X19";
		String type = "朝遗夕拾";
		String person = "YYY";
		String event = "心爱的海南狗走丢啦~ 亲们求找~ 特征：黑，黑，黑，还有黑。丢失地点：YYY寝室附近。...\n"
				+ "心爱的海南狗走丢啦~ 亲们求找~ 特征：黑，黑，黑，还有黑。丢失地点：YYY寝室附近。...";
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
				Toast.makeText(PersonalActivity.this, "发送成功!", Toast.LENGTH_SHORT).show();
				//refresh_this_activity
			}
			
		});
		
	}

	private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
 
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("nickname", "1楼@zxh");
        map.put("content", "aaaaabbbbccccdddddeeeeeffffggggggghhhhhhhhiiiiiiii");
        list.add(map);
 
        map = new HashMap<String, Object>();
        map.put("nickname", "2楼@wfz");
        map.put("content", "已找到，请加我陌陌");
        list.add(map);
 
        map = new HashMap<String, Object>();
        map.put("nickname", "3楼@lmc");
        map.put("content", "XNMBYY");
        list.add(map);
         
        return list;
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
