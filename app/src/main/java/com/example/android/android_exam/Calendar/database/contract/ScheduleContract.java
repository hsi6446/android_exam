package com.example.android.android_exam.Calendar.database.contract;

import android.provider.BaseColumns;

/**
 * Created by student on 2015-09-21.
 */
public class ScheduleContract {

    public static final String[] PROJECTION_ALL = new String[] {
                ScheduleEntry._ID,
                ScheduleEntry.COLUMN_NAME_DATE,
                ScheduleEntry.COLUMN_NAME_HOUR,
                ScheduleEntry.COLUMN_NAME_MINUTE,
                ScheduleEntry.COLUMN_NAME_CONTENTS

    };


    public ScheduleContract() {
    }

    public static abstract class ScheduleEntry implements BaseColumns {
        public static final String THIS_TABLE_NAME = "Schedule";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_HOUR = "hour";
        public static final String COLUMN_NAME_MINUTE = "minute";
        public static final String COLUMN_NAME_CONTENTS = "contents";
    }
}
