package com.example.anish.mycalendardemo.helperLib;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.anish.mycalendardemo.R;
import com.example.anish.mycalendardemo.databinding.ListItemsBinding;
import com.example.anish.mycalendardemo.model.CalendarEventModel;

import java.util.List;

/**
 * Created by anish on 24-01-2017.
 */

public class CustomListAdapter extends BaseAdapter {
    LayoutInflater inflter;

    Context context;
//    List<String> stringList;
    List<CalendarEventModel> eventModelList;

   /* public CustomListAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
    }*/

    public CustomListAdapter(Context context, List<CalendarEventModel> eventModelList) {
        this.context = context;
        this.eventModelList = eventModelList;
    }

  /*  @Override
    public int getCount() {
        return stringList.size();
    }*/
 @Override
    public int getCount() {
        return eventModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_items, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Log.e("Date",eventModelList.get(position).getEventDate());
        Log.e("count",eventModelList.get(position).getCount()+"");
        viewHolder.name.setText(eventModelList.get(position).getEventDate());
        viewHolder.no.setText(String.valueOf(eventModelList.get(position).getCount()));
//        viewHolder.no.setText(eventModelList.get(position).getCount());
        return view;
    }

    private class ViewHolder {
        TextView name,no;


        public ViewHolder(View view) {
            name = (TextView) view.findViewById(R.id.name);
            no=(TextView) view.findViewById(R.id.no);
        }

    }
}
