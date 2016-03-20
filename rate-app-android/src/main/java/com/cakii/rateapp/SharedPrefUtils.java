package com.cakii.rateapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created on 3/18/2016 AD.
 */
public class SharedPrefUtils {

    private static String PREF_KEY_USE_TIME = "rate_app_use_time";
    private static String PREF_KEY_START_DAY = "rate_app_start_day";
    private static String PREF_KEY_SHOULD_SHOW_DIALOG = "rate_app_should_show_dialog";

    public static SharedPreferences getSharedPreference(@NonNull Context context) {
        return context.getSharedPreferences("rateApp", Context.MODE_PRIVATE);
    }

    public static SharedPreferences.Editor getSharedPreferenceEditor(@NonNull Context context) {
        return getSharedPreference(context).edit();
    }

    public static void resetPromptRateApp(@NonNull Context context) {
        getSharedPreferenceEditor(context)
                .putInt(PREF_KEY_USE_TIME, 0)
                .putLong(PREF_KEY_START_DAY, new Date().getTime())
                .commit();
    }

    public static void increaseUseTime(@NonNull Context context) {
        getSharedPreferenceEditor(context)
                .putInt(PREF_KEY_USE_TIME, getUseTime(context) + 1)
                .commit();
    }

    public static int getUseTime(@NonNull Context context) {
        return getSharedPreference(context).getInt(PREF_KEY_USE_TIME, 0);
    }

    public static void setStartDate(@NonNull Context context) {
        getSharedPreferenceEditor(context)
                .putLong(PREF_KEY_START_DAY, new Date().getTime())
                .commit();
    }

    public static long getStartDate(@NonNull Context context) {
        return getSharedPreference(context).getLong(PREF_KEY_START_DAY, 0L);
    }

    public static void setShouldShowDialog(@NonNull Context context, boolean shouldShow) {
        getSharedPreferenceEditor(context)
                .putBoolean(PREF_KEY_SHOULD_SHOW_DIALOG, shouldShow)
                .commit();
    }

    public static boolean shouldShowDialog(@NonNull Context context) {
        return getSharedPreference(context).getBoolean(PREF_KEY_SHOULD_SHOW_DIALOG, true);
    }
}
