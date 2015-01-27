package com.example.campus_know_all;

import java.util.Calendar;

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

public class TimeActivity extends Activity {
private int year1;
private int month1;
private int day1;
private int year2;
private int month2;
private int day2;
private String date1;
private String date2;
public String date;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time);
		
		DatePicker dp1 = (DatePicker)findViewById(R.id.datePicker1);
		DatePicker dp2 = (DatePicker)findViewById(R.id.datePicker2);
		
		Calendar calendar = Calendar.getInstance();
		year1 = calendar.get(Calendar.YEAR); // 获取当前年份
		month1 = calendar.get(Calendar.MONTH); // 获取当前月份
		day1 = calendar.get(Calendar.DAY_OF_MONTH); // 获取当前日		
		year2 = calendar.get(Calendar.YEAR); // 获取当前年份
		month2 = calendar.get(Calendar.MONTH); // 获取当前月份
		day2 = calendar.get(Calendar.DAY_OF_MONTH); // 获取当前日	
		date1 = year1 + "年" + (month1+1) + "月" + day1 + "日";
		date2 = year2 + "年" + (month2+1) + "月" + day2 + "日";
		
		dp1.init(year1, month1, day1, new OnDateChangedListener(){

			@Override
			public void onDateChanged(DatePicker view, int year,
					int month, int day) {
				// TODO Auto-generated method stub
							
				TimeActivity.this.year1 = year;		
				TimeActivity.this.month1 = month;
				TimeActivity.this.day1 = day;
				date1 = year1 + "年" + (month1+1) + "月" + day1 + "日";
			}		
		});
		dp2.init(year2, month2, day2, new OnDateChangedListener(){

			@Override
			public void onDateChanged(DatePicker view, int year,
					int month, int day) {
				// TODO Auto-generated method stub
				TimeActivity.this.year2 = year;
				TimeActivity.this.month2 = month;
				TimeActivity.this.day2 = day;
				date2 = year2 + "年" + (month2+1) + "月" + day2 + "日";
			}		
		});		
		
		Button bt = (Button) findViewById(R.id.time_submit);
		bt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				date = date1 + "至" + date2;
				Intent it = new Intent();
				it.putExtra("date", date);
				setResult(0X717,it);
				finish();
			}
			
		});	
		
	}
}
