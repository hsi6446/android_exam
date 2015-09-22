
package com.example.android.android_exam.Calendar.database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.android_exam.Calendar.database.contract.ScheduleContract;

/**
 * Created by student on 2015-09-21.
 */
public class ScheduleDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Calendar.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CALENDAR_SQL_CREATE_ENTRIES = "CREATE TABLE " +
            ScheduleContract.ScheduleEntry.THIS_TABLE_NAME + " (" +
            ScheduleContract.ScheduleEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ScheduleContract.ScheduleEntry.COLUMN_NAME_DATE + " TEXT NOT NULL, " +
            ScheduleContract.ScheduleEntry.COLUMN_NAME_HOUR + " INTEGER NOT NULL, " +
            ScheduleContract.ScheduleEntry.COLUMN_NAME_MINUTE + " INTEGER NOT NULL, " +
            ScheduleContract.ScheduleEntry.COLUMN_NAME_CONTENTS + " TEXT NOT NULL" +
            ");";

    private static ScheduleDbHelper sSingleton = null;

    private ScheduleDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized ScheduleDbHelper getInstance(Context context) {
        if (sSingleton == null) {
            sSingleton = new ScheduleDbHelper(context);
        }
        return sSingleton;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CALENDAR_SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
