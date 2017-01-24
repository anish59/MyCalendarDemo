package com.example.anish.mycalendardemo;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.example.anish.mycalendardemo.databinding.ListDemoActivityBinding;
import com.example.anish.mycalendardemo.helperLib.CustomListAdapter;
import com.example.anish.mycalendardemo.model.CalendarEventModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anish on 24-01-2017.
 */

public class ListDemoActivity extends Activity {
    CalendarEventModel calendarEventModel;

    ListDemoActivityBinding binding;
    CustomListAdapter listAdapter;
    List<String> stringList;
    List<CalendarEventModel> eventModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.list_demo_activity);
//        stringList=new ArrayList<>();
//        stringList.add("Salman");
//        stringList.add("Sharukh");
//        stringList.add("Amir");

       CalendarEventModel eventModel1,eventModel2;
        eventModelList=new ArrayList<>();

        eventModel1=new CalendarEventModel();
        eventModel1.setEventDate("aaaa");
        eventModel1.setCount(1);

        eventModelList.add(eventModel1);

        eventModel2=new CalendarEventModel();
        eventModel2.setEventDate("bbb");
        eventModel2.setCount(2);
        eventModelList.add(eventModel2);


//        listAdapter=new CustomListAdapter(this,stringList);
        listAdapter=new CustomListAdapter(this,eventModelList);
        binding.lv.setAdapter(listAdapter);
    }
}
