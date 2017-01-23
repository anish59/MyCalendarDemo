package com.example.anish.mycalendardemo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.anish.mycalendardemo.helperLib.MFCalendarView;
import com.example.anish.mycalendardemo.helperLib.MFCalendarView2;
import com.example.anish.mycalendardemo.helperLib.Util;
import com.example.anish.mycalendardemo.helperLib.onMFCalendarViewListener;
import com.example.anish.mycalendardemo.model.CalendarEventModel;

public class MainActivity extends Activity {

	MFCalendarView2 mf;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mf = (MFCalendarView2) findViewById(R.id.mFCalendarView);

		mf.setOnCalendarViewListener(new onMFCalendarViewListener() {
			
			@Override
			public void onDisplayedMonthChanged(int month, int year, String monthStr) {

				StringBuffer bf = new StringBuffer()
				.append(" month:")
				.append(month)
				.append(" year:")
				.append(year)
				.append(" monthStr: ")
				.append(monthStr);
				
				Toast.makeText(MainActivity.this,  bf.toString(),
						Toast.LENGTH_SHORT).show();
				
			}
			
			@Override
			public void onDateChanged(String date) {
			
				Toast.makeText(MainActivity.this, "onDateChanged:" + date,Toast.LENGTH_SHORT).show(); //yyyy-mm-dd
			}
		});
		
		/**
		 * you can set calendar date anytime
		 * */
		//mf.setDate("2014-02-19");
		
		
		/**
		 * calendar events samples 
		 * */
    CalendarEventModel eventModel=new CalendarEventModel();
        eventModel.setEventDate("2017-01-26");
		ArrayList<CalendarEventModel> eventDays = new ArrayList<CalendarEventModel>();
		eventDays.add(eventModel);
	/*	eventDays.add("2017-01-26");
		eventDays.add(Util.getTomorrow());
		eventDays.add(Util.getCurrentDate());*/
		
		
		mf.setCalEvents(eventDays);
		
		Log.e("","locale:" + Util.getLocale());
		
	}
}
