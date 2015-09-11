
package com.example.android.android_exam.Calendar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.android.android_exam.Calendar.adapter.CalendarAdapter;
import com.example.android.android_exam.Calendar.adapter.ScheduleAdapter;
import com.example.android.android_exam.Calendar.model.Schedule;
import com.example.android.android_exam.Calendar.view.CalendarView;
import com.example.android.android_exam.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private List<Calendar> mList;
    private CalendarAdapter mCalendarAdapter;
    private CalendarView mCalendarView;
    private TextView mTitleTextView;
    private ListView mTodoListView;
    private ScheduleAdapter mScheduleAdapter;

    private Map<Calendar, List<Schedule>> mScheduleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mScheduleMap = new HashMap<>();

        // 버튼 이벤트 연결
        findViewById(R.id.prev_btn).setOnClickListener(this);
        findViewById(R.id.next_btn).setOnClickListener(this);
        mTitleTextView = (TextView) findViewById(R.id.title_text_view);

        // 어뎁터 준비

        mCalendarView = (CalendarView) findViewById(R.id.calendar);
        mTodoListView = (ListView) findViewById(R.id.lv_todo);

        // view 에 어뎁터를 설정
        mCalendarAdapter = new CalendarAdapter(this);
        mCalendarView.setAdapter(mCalendarAdapter);

        // 아이템 클릭 이벤트 연결
        mCalendarView.setOnItemClickListener(this);
        mCalendarView.setOnItemLongClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prev_btn:
                mCalendarAdapter.prevMonth();
                updateTitle();
                break;
            case R.id.next_btn:
                mCalendarAdapter.nextMonth();
                updateTitle();
                break;

            default:
                break;
        }

    }

    private void updateTitle() {
        int year = mCalendarAdapter.getCalendar().get(Calendar.YEAR);
        int month = mCalendarAdapter.getCalendar().get(Calendar.MONTH) + 1;
        mTitleTextView.setText(year + "년 " + month + "월");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mCalendarAdapter.setSelectedPosition(position);
        mCalendarAdapter.notifyDataSetChanged();

        Calendar calendar = (Calendar) mCalendarAdapter.getItem(position);
        List<Schedule> list = mScheduleMap.get(calendar);

        if (list == null) {
            list = Collections.emptyList();
        }

        mScheduleAdapter = new ScheduleAdapter(CalendarActivity.this, list);
        mTodoListView.setAdapter(mScheduleAdapter);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        View layout = getLayoutInflater().inflate(R.layout.dialog_schedule, null);

        final TimePicker timePicker = (TimePicker) layout.findViewById(R.id.picker_time);
        final EditText editText = (EditText) layout.findViewById(R.id.et_schedule);
        final Calendar calendar = (Calendar) mCalendarAdapter.getItem(position);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setNegativeButton("닫기", null);
        builder.setPositiveButton("저장", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                Schedule schedule = new Schedule(timePicker.getCurrentHour(),
                        timePicker.getCurrentMinute(),
                        editText.getText().toString());

                List<Schedule> list = mScheduleMap.get(calendar);

                if (list == null) {
                    //초기화
                    list = new ArrayList<>();
                }

                mCalendarAdapter.setScheduledPosition(position);
                mCalendarAdapter.notifyDataSetChanged();

                list.add(schedule);

                mScheduleMap.put(calendar, list);

                mScheduleAdapter = new ScheduleAdapter(CalendarActivity.this, list);
                mTodoListView.setAdapter(mScheduleAdapter);
            }
        });

        builder.setView(layout);
        builder.show();


        return true;
    }
}
