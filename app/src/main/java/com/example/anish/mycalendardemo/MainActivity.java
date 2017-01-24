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


    MFCalendarView mf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mf = (MFCalendarView) findViewById(R.id.mFCalendarView);

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

                Toast.makeText(MainActivity.this, bf.toString(),
                        Toast.LENGTH_SHORT).show();
                addEventAndCounter();
            }

            @Override
            public void onDateChanged(String date) {

                Toast.makeText(MainActivity.this, "onDateChanged:" + date, Toast.LENGTH_SHORT).show(); //yyyy-mm-dd
            }
        });

        /**
         * you can set calendar date anytime
         * */
        //mf.setDate("2014-02-19");


        /**
         * calendar events samples
         * */


        Log.e("", "locale:" + Util.getLocale());


        addEventAndCounter();

    }

    private void addEventAndCounter() {
        ArrayList<String> dd= new ArrayList<String>();
        ArrayList<Integer> cc = new ArrayList<>();


        dd.add("2017-01-25");
        cc.add(5);

        dd.add("2017-01-26");
        cc.add(6);

      /*  dd.add(Util.getTomorrow());
        dd.add(Util.getCurrentDate());*/
        mf.setEvents(dd,cc);
    }


}
/*

    ArrayList<CalendarEventModel> eventDays = new ArrayList<CalendarEventModel>();


    CalendarEventModel eventModel = new CalendarEventModel();
eventModel.setEventDate("2017-01-26");
        eventModel.setCount(1);
        eventDays.add(eventModel);


        CalendarEventModel eventModel2 = new CalendarEventModel();
        eventModel2.setEventDate("2017-01-25");
        eventModel2.setCount(2);
        eventDays.add(eventModel2);


*/
/*		ArrayList<String> cc = new ArrayList<String>();

		cc.add("2017-01-26");
		cc.add("2017-01-26");
		cc.add(Util.getTomorrow());
		cc.add(Util.getCurrentDate());*//*


        mf.setCalEvents(eventDays);
//		mf.setEvents(cc);
*/
