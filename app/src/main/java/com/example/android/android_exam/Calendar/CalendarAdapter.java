package com.example.android.android_exam.Calendar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Calendar;
import java.util.List;

/**
 * Created by student on 2015-09-08.
 */
public class CalendarAdapter extends BaseAdapter {

    private List<Calendar> mList;
    private Context mContext;

    public CalendarAdapter(Context context, List<Calendar> list) {
        this.mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
