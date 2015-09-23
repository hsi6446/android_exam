package com.example.android.android_exam.Calendar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.android_exam.Calendar.database.contract.ScheduleContract;
import com.example.android.android_exam.Calendar.database.helper.ScheduleDbHelper;
import com.example.android.android_exam.Calendar.model.Schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by student on 2015-09-22.
 * 스케줄 관리 facade Class
 */
public class ScheduleFacade {
    private ScheduleDbHelper mScheduleDbHelper;
    private SimpleDateFormat mFormat;

    public ScheduleFacade(Context context) {
        mScheduleDbHelper = ScheduleDbHelper.getInstance(context);
        mFormat = new SimpleDateFormat("yyyy-mm-dd");
    }

    public boolean addSchedule(Calendar calendar, Schedule schedule){

        ContentValues values = new ContentValues();

        values.put(ScheduleContract.ScheduleEntry.COLUMN_NAME_DATE, mFormat.format(calendar.getTime()));
        values.put(ScheduleContract.ScheduleEntry.COLUMN_NAME_HOUR, schedule.getHour());
        values.put(ScheduleContract.ScheduleEntry.COLUMN_NAME_MINUTE, schedule.getMinute());
        values.put(ScheduleContract.ScheduleEntry.COLUMN_NAME_CONTENTS, schedule.getContents());

        return mScheduleDbHelper.getWritableDatabase().insert(
                ScheduleContract.ScheduleEntry.THIS_TABLE_NAME,
                null,
                values
        ) != -1;

    }

    public  List<Schedule> getSchedule(Calendar calendar){
        String calStr = mFormat.format(calendar.getTime());

        Cursor cursor = mScheduleDbHelper.getReadableDatabase().query(
                ScheduleContract.ScheduleEntry.THIS_TABLE_NAME,
                ScheduleContract.PROJECTION_ALL,
                ScheduleContract.ScheduleEntry.COLUMN_NAME_DATE + "=?",
                new String[] {
                        calStr },
                null,
                null,
                ScheduleContract.ScheduleEntry.COLUMN_NAME_HOUR + " DESC, "
                + ScheduleContract.ScheduleEntry.COLUMN_NAME_MINUTE + " DESC"
        );


        return cursorToList(cursor);
    }

    public List<Schedule> cursorToList(Cursor cursor) {
        List<Schedule> list = new ArrayList<>();

        if (cursor != null) {


            while (cursor.moveToNext()) {
                int hour = cursor.getInt(cursor.getColumnIndexOrThrow(ScheduleContract.ScheduleEntry.COLUMN_NAME_HOUR));
                int minute = cursor.getInt(cursor.getColumnIndexOrThrow(ScheduleContract.ScheduleEntry.COLUMN_NAME_MINUTE));
                String contents = cursor.getString(cursor.getColumnIndexOrThrow(ScheduleContract.ScheduleEntry.COLUMN_NAME_CONTENTS));

                long id = cursor.getLong(cursor.getColumnIndexOrThrow(ScheduleContract.ScheduleEntry._ID));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(ScheduleContract.ScheduleEntry.COLUMN_NAME_DATE));

                Schedule schedule = new Schedule(id, date, hour, minute, contents);
                list.add(schedule);
            }
            cursor.close();
        }
        return list;
    }

    /**
     * 스케줄 삭제
     * @param schedule 삭제할 스케줄
     * @return 성공/실패
     */
    public boolean removeSchedule(Schedule schedule) {
        return mScheduleDbHelper.getWritableDatabase().delete(
                ScheduleContract.ScheduleEntry.THIS_TABLE_NAME,
                //TODO date 비교조건 추가
                ScheduleContract.ScheduleEntry.COLUMN_NAME_HOUR + "=? AND " +
                        ScheduleContract.ScheduleEntry.COLUMN_NAME_MINUTE + "=?",
                new String[] {"" + schedule.getHour(), "" + schedule.getMinute() }
        ) != -1;

    }

    /**
     * 스케줄 수정
     *
     * @param schedule 기존 데이타에 변경할 값을 셋팅해서 받아야 함.
     * @return he number of rows affected - 영향을 받은 rows 의 갯수
     */
    public int modifySchedule(Schedule schedule) {
        SQLiteDatabase db = mScheduleDbHelper.getWritableDatabase();

        // 패스워드 변경
        ContentValues values = new ContentValues();
        values.put(ScheduleContract.ScheduleEntry.COLUMN_NAME_HOUR, schedule.getHour());
        values.put(ScheduleContract.ScheduleEntry.COLUMN_NAME_MINUTE, schedule.getMinute());
        values.put(ScheduleContract.ScheduleEntry.COLUMN_NAME_CONTENTS, schedule.getContents());

        // 조건 = Email 이 ? 과 같다면
        String selection = ScheduleContract.ScheduleEntry._ID + "=?";

        // ? 에 들어갈 값을 바인딩
        String[] selectionArgs = { schedule.getId() + ""};

        int count = db.update(
                ScheduleContract.ScheduleEntry.THIS_TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }
}
