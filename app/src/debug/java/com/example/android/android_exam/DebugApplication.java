package com.example.android.android_exam;

import com.facebook.stetho.Stetho;

/**
 * Created by student on 2015-10-07.
 */
public class DebugApplication extends parseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initialize(Stetho.newInitializerBuilder(this)
        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        .build());

    }
}
