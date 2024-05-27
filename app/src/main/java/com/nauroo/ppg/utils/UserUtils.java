package com.nauroo.ppg.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.nauroo.ppg.model.User;


public class UserUtils {


    public static String getUserId(Context context) {
        if (context != null) {
            return PreferenceUtil.getStringFromPref(context, Constants.IS_REGISTERED);
        }
        return null;
    }

    public static void setUserId(Context context, String id)
    {
        if (context != null) {
            PreferenceUtil.addStringToPref(context, Constants.IS_REGISTERED, id);
        }

    }

    public static void setUserProfile(Context context, User user) {
        if (context != null) {
            PreferenceUtil.addStringToPref(context, Constants.IS_REGISTERED, user.getName());
            PreferenceUtil.addStringToPref(context, Constants.SP_USER_PROFILE, new Gson().toJson(user));
        }
    }

    public static User getUserProfile(Context context) {
        if (context != null) {
            return new Gson().fromJson(PreferenceUtil.getStringFromPref(context, Constants.SP_USER_PROFILE), User.class);
        }
        return null;
    }
}
