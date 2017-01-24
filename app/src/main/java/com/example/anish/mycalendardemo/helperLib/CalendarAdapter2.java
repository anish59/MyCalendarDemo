package com.example.anish.mycalendardemo.helperLib;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anish.mycalendardemo.R;
import com.example.anish.mycalendardemo.model.CalendarEventModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarAdapter2 extends BaseAdapter {
	private Context mContext;

	private Calendar month;
	public GregorianCalendar pmonth; // calendar instance for previous month
	/**
	 * calendar instance for previous month for getting complete view
	 */
	public GregorianCalendar pmonthmaxset;
	private GregorianCalendar selectedDate;
	private int firstDay;
	private int maxWeeknumber;
	private int maxP;
	private int calMaxP;
	int lastWeekDay;
	int leftDays;
	private int mnthlength;
	private String itemvalue, curentDateString;
	DateFormat df;

	private ArrayList<String> items;
	private ArrayList<CalendarEventModel> items2;
	public static List<String> dayString;
	private View previousView;

	Calendar a;

	public CalendarAdapter2(Context c, GregorianCalendar monthCalendar) {
	
		mContext = c;
		initCalendarAdapter(monthCalendar, null);
		
	}
	
	public void initCalendarAdapter(GregorianCalendar monthCalendar,onMFCalendarViewListener calendarListener){
		CalendarAdapter2.dayString = new ArrayList<>();
		month = monthCalendar;
		
		selectedDate = (GregorianCalendar) monthCalendar.clone();
		
		month.set(GregorianCalendar.DAY_OF_MONTH, 1);
		this.items2 = new ArrayList<>();
		
		adaptersetDate(selectedDate, calendarListener);
		refreshDays();
	}

	/*public void setItems(ArrayList<String> items) {
		
		if (items == null) 
			return;
		
		for (int i = 0; i != items.size(); i++) {
			if (items.get(i).length() == 1) {
				items.set(i, "0" + items.get(i));
			}
		}
		this.items = items;
	}*/

	public void setObjectItems(ArrayList<CalendarEventModel> items) {

		if (items == null)
			return;

		/*for (int i = 0; i != items.size(); i++) {
			if (items.get(i).getEventDate().length() == 1) {
				items.set(i,getItem(i).);
			}*/
		this.items2 = items;
	}
	


	public int getCount() {
		return items2.size();
	}

	public Object getItem(int position) {
		return items2.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new view for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		TextView dayView,dayViewCount;
		if (convertView == null) { // if it's not recycled, initialize some
									// attributes
			LayoutInflater vi = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.calendar_item, null);

		}
		dayView = (TextView) v.findViewById(R.id.date);
		dayViewCount = (TextView) v.findViewById(R.id.total_event_count);
		// separates daystring into parts.
		String[] separatedTime = items2.get(position).getEventDate().split("-");
		// taking last part of date. ie; 2 from 2012-12-02
		String gridvalue = separatedTime[2].replaceFirst("^0*", "");
		// checking whether the day is in current month or not.
		if ((Integer.parseInt(gridvalue) > 1) && (position < firstDay)) {
			// setting offdays to white color.
			dayView.setTextColor(Color.WHITE);
			dayView.setClickable(false);
			dayView.setFocusable(false);
		} else if ((Integer.parseInt(gridvalue) < 15) && (position > 28)) {
			dayView.setTextColor(Color.WHITE);
			dayView.setClickable(false);
			dayView.setFocusable(false);
		} else{
			// setting curent month's days in blue color.
			dayView.setTextColor(v.getResources().getColor(R.color.blue));
		}

		if (items2.get(position).getEventDate().equals(curentDateString)) {
			setSelected(v);
			previousView = v;
		} else {
			v.setBackgroundResource(R.drawable.list_item_background);
		}
		dayView.setText(gridvalue);
		dayViewCount.setText(items2.get(position).getCount());

		// create date string for comparison
		String date = items2.get(position).getEventDate();

		if (date.length() == 1) {
			date = "0" + date;
		}
		String monthStr = "" + (month.get(GregorianCalendar.MONTH) + 1);
		if (monthStr.length() == 1) {
			monthStr = "0" + monthStr;
		}

		// show icon if date is not empty and it exists in the items array
		ImageView iw = (ImageView) v.findViewById(R.id.date_icon);
		if (date.length() > 0 && items2 != null && items2.contains(date)) {
			iw.setVisibility(View.VISIBLE);
		} else {
			iw.setVisibility(View.INVISIBLE);
		}
		return v;
	}

	public View setSelected(View view) {
		if (previousView != null) {
			previousView.setBackgroundResource(R.drawable.list_item_background);
		}
		previousView = view;
		view.setBackgroundResource(R.drawable.calendar_cel_selectl);
		return view;
	}

	public void refreshDays() {
		// clear items
		items2.clear();
		dayString.clear();

		pmonth = (GregorianCalendar) month.clone();
		// month start day. ie; sun, mon, etc
		firstDay = month.get(GregorianCalendar.DAY_OF_WEEK);
		// finding number of weeks in current month.
		maxWeeknumber = month.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH);
		// allocating maximum row number for the gridview.
		mnthlength = maxWeeknumber * 7;
		maxP = getMaxP(); // previous month maximum day 31,30....
		calMaxP = maxP - (firstDay - 1);// calendar offday starting 24,25 ...
		/**
		 * Calendar instance for getting a complete gridview including the three
		 * month's (previous,current,next) dates.
		 */
		pmonthmaxset = (GregorianCalendar) pmonth.clone();
		/**
		 * setting the start date as previous month's required date.
		 */
		pmonthmaxset.set(GregorianCalendar.DAY_OF_MONTH, calMaxP + 1);

		/**
		 * filling calendar gridview.
		 */
		for (int n = 0; n < mnthlength; n++) {

			itemvalue = df.format(pmonthmaxset.getTime());
			pmonthmaxset.add(GregorianCalendar.DATE, 1);
			dayString.add(itemvalue);

		}
	}

	private int getMaxP() {
		int maxP;
		if (month.get(GregorianCalendar.MONTH) == month.getActualMinimum(GregorianCalendar.MONTH)) {
			pmonth.set((month.get(GregorianCalendar.YEAR) - 1),
					month.getActualMaximum(GregorianCalendar.MONTH), 1);
		} else {
			pmonth.set(GregorianCalendar.MONTH,
					month.get(GregorianCalendar.MONTH) - 1);
		}
		maxP = pmonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

		return maxP;
	}
	
	public void adaptersetDate(GregorianCalendar monthCalendar, onMFCalendarViewListener c){
		
		df = new SimpleDateFormat("yyyy-MM-dd", Util.getLocale());
		selectedDate = monthCalendar;
		curentDateString = df.format(selectedDate.getTime());
		
		//Log.d("","CalendarAdapter selectedDate:" + curentDateString);
		
	/*	if (c != null) 
			c.onDateChanged(curentDateString);
	*/	
	}
	
	public String getSelectedDate(){
		return curentDateString;
	}
	

}