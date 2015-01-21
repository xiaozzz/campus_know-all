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
		
		//----------------------����Ϊί������------------------------------		
		//����id���ӷ��������ó� ʱ��(time)�ص�(place)���(type)�¼�(event)
		//Handle_SchoolActivity(id);
		String time = "2015��1��1��~2015��2��1��";
		String place = "X19";
		String type = "����Ϧʰ";
		String person = "YYY";
		String event = "�İ��ĺ��Ϲ��߶���~ ��������~ �������ڣ��ڣ��ڣ����кڡ���ʧ�ص㣺YYY���Ҹ�����...\n"
				+ "�İ��ĺ��Ϲ��߶���~ ��������~ �������ڣ��ڣ��ڣ����кڡ���ʧ�ص㣺YYY���Ҹ�����...";
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
		
		//----------------------����Ϊ�ظ�------------------------------
		
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
		
		//----------------------����Ϊ�ظ�------------------------------
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
				Toast.makeText(PersonalActivity.this, "���ͳɹ�!", Toast.LENGTH_SHORT).show();
				//refresh_this_activity
			}
			
		});
		
	}

	private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
 
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("nickname", "1¥@zxh");
        map.put("content", "aaaaabbbbccccdddddeeeeeffffggggggghhhhhhhhiiiiiiii");
        list.add(map);
 
        map = new HashMap<String, Object>();
        map.put("nickname", "2¥@wfz");
        map.put("content", "���ҵ��������İİ");
        list.add(map);
 
        map = new HashMap<String, Object>();
        map.put("nickname", "3¥@lmc");
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
