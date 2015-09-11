
package com.example.android.android_exam.Calendar.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.android_exam.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by student on 2015-09-08.
 */
public class CalendarAdapter extends BaseAdapter {

    private List<Calendar> mList;
    private Context mContext;
    private Calendar mCalendar;
    private int mSelectedPosition = -1;


    public CalendarAdapter(Context context) {
        mContext = context;

        // 오늘 날짜
        mCalendar = GregorianCalendar.getInstance();
        createCalendar(mCalendar);


    }

    private void createCalendar(Calendar calendar) {

        mList = new ArrayList<>();

        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);

        // 마지막 날
        int lastDay = mCalendar.getActualMaximum(Calendar.DATE);

        // 이 달의 첫 번째 날
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);
        int firstday = mCalendar.get(Calendar.DAY_OF_WEEK);

        // 공백
        for (int i = 1; i < firstday; i++) {
            mList.add(null);
        }

        // 이번 달 달력 데이터
        for (int i = 1; i <= lastDay; i++) {
            mList.add(new GregorianCalendar(year, month, i));
        }
    }

    public void prevMonth() {

        changeMonth(-1);
    }

    public void nextMonth() {
        changeMonth(1);

    }

    public Calendar getCalendar() {
        return mCalendar;
    }

    private void changeMonth(int month) {

        //선택 안 한 상태
        mSelectedPosition = -1;

        // 다음달로 설정
        mCalendar.add(Calendar.MONTH, month);
        createCalendar(mCalendar);

        // 어뎁터에 바뀐 데이터를 반영하도록 알려 줌
        notifyDataSetChanged();
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

    // 아이템이 화면에 보일 때 호출 됨.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder holder;

        // 레이아웃을 완성하고 뷰홀더와 연결
        // findViewById를 한번만 사용하기 위해.
        if (convertView == null) {
            holder = new ViewHolder();
            // 처음 로드
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_calendar, parent,
                    false);

            holder.dateTextView = (TextView) convertView.findViewById(R.id.date_text_view);

            convertView.setTag(holder);
        } else {
            // 재사용
            holder = (ViewHolder) convertView.getTag();

        }

        // 데이터를 레이아웃에 설정
        Calendar calendar = mList.get(position);
        if (calendar != null) {
            holder.dateTextView.setText("" + calendar.get(calendar.DATE));

            if (position % 7 == 0) {
                holder.dateTextView.setTextColor(Color.RED);
            } else if ((position + 1) % 7 == 0) {
                holder.dateTextView.setTextColor(Color.BLUE);
            } else {
                holder.dateTextView.setTextColor(Color.BLACK);
            }
        } else {
            holder.dateTextView.setText("");
        }

        if (position == mSelectedPosition) {

            //선택된 셀 배경 색상 변경
            convertView.setBackgroundColor(Color.YELLOW);
        } else {

            convertView.setBackgroundColor(Color.WHITE);
        }


        return convertView;
    }

    public int getSelectedPosition() {
        return mSelectedPosition;
    }

    public void setSelectedPosition(int SelectedPosition) {
        this.mSelectedPosition = SelectedPosition;
    }


    static class ViewHolder {
        TextView dateTextView;
    }
}
