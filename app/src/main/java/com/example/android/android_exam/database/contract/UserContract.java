
package com.example.android.android_exam.database.contract;

import android.provider.BaseColumns;

/**
 * Created by student on 2015-09-18.
 */
public class UserContract {

    public UserContract() {
    }

    public static abstract class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "User";
        public static final String COLUMN_NAME_NICKNAME = "nickname";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }

}
