package com.example.campus_know_all;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_school);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		int id = bundle.getInt("pos");
		username = bundle.getString("username");
		//处理id，从服务器端拿出 时间(time)地点(place)类别(type)事件(event)
		//Handle_SchoolActivity(id);
		String time = "2015年1月1日~2015年2月1日";
		String place = "东区大转盘";
		String type = "人文";
		String event = "第一节校文化节吹B大会正式开幕,....\n第一节校文化节吹B大会正式开幕,....\n第一节校文化节吹B大会正式开幕,....";
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
