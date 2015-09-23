
package com.example.android.android_exam.Calendar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.android.android_exam.Calendar.adapter.CalendarAdapter;
import com.example.android.android_exam.Calendar.adapter.ScheduleAdapter;
import com.example.android.android_exam.Calendar.database.ScheduleFacade;
import com.example.android.android_exam.Calendar.model.Schedule;
import com.example.android.android_exam.Calendar.view.CalendarView;
import com.example.android.android_exam.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private CalendarAdapter mCalendarAdapter;
    private CalendarView mCalendarView;
    private TextView mTitleTextView;
    private ListView mTodoListView;
    private ScheduleAdapter mScheduleAdapter;

    private ScheduleFacade mScheduleFacade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // TODO DB Helper 초기화
        mScheduleFacade = new ScheduleFacade(this);

        // 버튼 이벤트 연결
        findViewById(R.id.prev_btn).setOnClickListener(this);
        findViewById(R.id.next_btn).setOnClickListener(this);

        // 어뎁터 준비

        mCalendarView = (CalendarView) findViewById(R.id.calendar);
        mTodoListView = (ListView) findViewById(R.id.lv_todo);

        // view 에 어뎁터를 설정
        mCalendarAdapter = new CalendarAdapter(this);
        mCalendarView.setAdapter(mCalendarAdapter);

        // 아이템 클릭 이벤트 연결
        mCalendarView.setOnItemClickListener(this);
        mCalendarView.setOnItemLongClickListener(this);

        // 컨텍스트 메뉴 연결
        registerForContextMenu(mTodoListView);

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

        }
        updateTitle();
    }

    private void updateTitle() {
        int year = mCalendarAdapter.getCalendar().get(Calendar.YEAR);
        int month = mCalendarAdapter.getCalendar().get(Calendar.MONTH) + 1;
        mTitleTextView.setText(year + "년 " + month + "월");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        loadSchedule(position);

    }

    private void loadSchedule(int position) {
        mCalendarAdapter.setSelectedPosition(position);
        mCalendarAdapter.notifyDataSetChanged();

        Calendar calendar = (Calendar) mCalendarAdapter.getItem(position);
        List<Schedule> list = mScheduleFacade.getSchedule(calendar);

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

                // TODO DB에서 데이타 얻어옴. query 할 부분
                List<Schedule> list = mScheduleFacade.getSchedule(calendar);

                if (list == null) {
                    // 초기화
                    list = new ArrayList<>();
                }

                list.add(schedule);

                mScheduleFacade.addSchedule(calendar, schedule);

                mScheduleAdapter = new ScheduleAdapter(CalendarActivity.this, list);
                mTodoListView.setAdapter(mScheduleAdapter);

            }
        });

        builder.setView(layout);
        builder.show();


        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);

    }

    // context 메뉴 처리
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        // adapter 의 정보를 얻을 수 있는 객체
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete:
                // TODO 삭제를 확인하는 다이얼로그 띄우기
                Schedule remove = (Schedule) mScheduleAdapter.getItem(info.position);
                if (mScheduleFacade.removeSchedule(remove)) {
                    Toast.makeText(CalendarActivity.this, "삭제 ㅇㅋ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CalendarActivity.this, "에러 ㅇㅇ", Toast.LENGTH_SHORT).show();
                }

                // 달력을 다시 클릭한 것처럼 해서 DB 에서 다시 데이터 로드
                loadSchedule(mCalendarAdapter.getSelectedPosition());

                return true;
            case R.id.modify:
                // TODO 1. 수정 다이얼로그 띄우기
                View layout = getLayoutInflater().inflate(R.layout.dialog_schedule, null);
                final TimePicker timePicker = (TimePicker) layout.findViewById(R.id.picker_time);
                final EditText editText = (EditText) layout.findViewById(R.id.et_schedule);

                final Calendar calendar = (Calendar) mCalendarAdapter.getItem(info.position);

                // 기존 데이타를 다이얼로그에 표시
                final Schedule schedule = (Schedule)mScheduleAdapter.getItem(info.position);
                timePicker.setCurrentHour(schedule.getHour());
                timePicker.setCurrentMinute(schedule.getMinute());
                editText.setText(schedule.getContents());

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setNegativeButton("닫기", null);
                builder.setPositiveButton("수정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        schedule.setHour(timePicker.getCurrentHour());
                        schedule.setMinute(timePicker.getCurrentMinute());
                        schedule.setContents(editText.getText().toString());

                        if(mScheduleFacade.modifySchedule(schedule) != 0) {
                            Toast.makeText(CalendarActivity.this, "수정 ㅇㅋ", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(CalendarActivity.this, "실패ㅋ", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

                builder.setView(layout);
                builder.show();

                loadSchedule(mCalendarAdapter.getSelectedPosition());

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

}
