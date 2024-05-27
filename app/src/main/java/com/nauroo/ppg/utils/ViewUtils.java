package com.nauroo.ppg.utils;

import android.content.Context;

import com.nauroo.ppg.R;

/**
 * Created by Mohan M on 1/22/2018.
 */

public class ViewUtils {
    public static boolean isTablet(Context context) {
        return context.getResources().getBoolean(R.bool.portrait_only
        );
    }
}
