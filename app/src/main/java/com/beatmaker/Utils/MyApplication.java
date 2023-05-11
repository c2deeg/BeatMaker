package com.beatmaker.Utils;



import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

public class MyApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(getApplicationContext());
        new WebServices();
//        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
