package com.example.android.android_exam.Calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.android_exam.R;

import java.util.Calendar;
import java.util.List;

/**
 * Created by student on 2015-09-08.
 */
public class CalendarAdapter extends BaseAdapter {

    private List<Calendar> mList;
    private Context mContext;

    public CalendarAdapter(Context context, List<Calendar> list) {
        mList = list;
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


    //아이템이 화면에 보일 때 호출 됨.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        //레이아웃을 완성
        if (convertView == null) {
            holder = new ViewHolder();
            //처음 로드
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_calendar, parent);

            holder.dateTextView = (TextView)convertView.findViewById(R.id.date_text_view);

            convertView.setTag(holder);
        } else  {
            //재사용
            holder = (ViewHolder)convertView.getTag();

        }

        //데이터를 레이아웃에 설정

        return convertView;
    }

    static class ViewHolder {
        TextView dateTextView;
    }
}
