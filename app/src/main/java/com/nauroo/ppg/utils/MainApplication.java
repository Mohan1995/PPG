package com.nauroo.ppg.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by Mohan M on 12/18/2017.
 */

public class MainApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
}