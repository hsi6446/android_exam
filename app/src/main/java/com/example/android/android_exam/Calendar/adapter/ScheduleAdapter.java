package com.example.android.android_exam.Calendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.android_exam.Calendar.model.Schedule;
import com.example.android.android_exam.R;

import java.util.List;

/**
 * Created by student on 2015-09-11.
 */
public class ScheduleAdapter extends BaseAdapter {

    private static final String TAG = ScheduleAdapter.class.getSimpleName();
    private List<Schedule> mScheduleList;
    private Context mContext;

    public ScheduleAdapter(Context context, List<Schedule> data) {

        mScheduleList = data;
        mContext = context;

    }

    @Override
    public int getCount() {
        return mScheduleList.size();
    }

    @Override
    public Object getItem(int position) {
        return mScheduleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // 레이아웃을 완성하고 뷰홀더와 연결
        // findViewById를 한번만 사용하기 위해.
        if (convertView == null) {
            holder = new ViewHolder();
            // 처음 로드
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_schedule, parent,
                    false);

            holder.todoTextView = (TextView) convertView.findViewById(R.id.tv_todo);

            convertView.setTag(holder);
        } else {
            // 재사용
            holder = (ViewHolder) convertView.getTag();

        }

        Schedule schedule = mScheduleList.get(position);
        holder.todoTextView.setText(schedule.toString());


        return convertView;
    }


    private class ViewHolder {

        TextView todoTextView;
    }
}
