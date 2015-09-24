package com.example.android.android_exam;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by student on 2015-09-24.
 */
public class parseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "IpfEScrN3IovSMzK3WopM9CkMPjwsUBZUeIMS3w9", "0AvEWCAn3SMqgmazPwtAzkhgKOzMSXBDrpNMAbzI");

    }
}
