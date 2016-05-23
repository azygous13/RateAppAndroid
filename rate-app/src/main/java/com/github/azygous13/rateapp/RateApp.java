package com.github.azygous13.rateapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import java.util.Date;

/**
 * Created on 3/18/2016 AD.
 */
public class RateApp {

    private static RateApp rateApp;
    private int useUntilPrompt = 10;
    private int dayUntilPrompt = 7;
    private boolean isDebug = false;
    private boolean isCancelable = false;
    private boolean isShowIcon = true;

    public static RateApp init() {
        if (rateApp == null) {
            rateApp = new RateApp();
        }
        return rateApp;
    }

    private RateApp() {

    }

    public RateApp useUntilPrompt(int time) {
        useUntilPrompt = time;
        return rateApp;
    }

    public RateApp dayUntilPrompt(int day) {
        dayUntilPrompt = day;
        return rateApp;
    }

    public RateApp setDebug(boolean debug) {
        isDebug = debug;
        return rateApp;
    }

    public RateApp cancelable(boolean cancelable) {
        isCancelable = cancelable;
        return rateApp;
    }

    public RateApp setShowIcon(boolean showIcon) {
        isShowIcon = showIcon;
        return rateApp;
    }

    public void monitor(@NonNull FragmentActivity activity) {
        SharedPrefUtils.increaseUseTime(activity);
        if (shouldShowDialog(activity)) {
            String title = activity.getString(R.string.rate_app_title);
            String later = activity.getString(R.string.rate_app_later);
            String rateNow = activity.getString(R.string.rate_app_now);
            String noThanks = activity.getString(R.string.rate_app_never);
            String description = activity.getString(R.string.rate_app_description);

            RateAppDialog
                    .newInstance(title, description, later, noThanks, rateNow, isCancelable, isShowIcon)
                    .show(activity.getSupportFragmentManager(), "RateApp");
        }
    }

    private boolean shouldShowDialog(Context context) {
        return isDebug
                || isFirstLaunch(context)
                || !isIgnoreDialog(context)
                || isOverUseTime(context)
                || isOverDay(context);
    }

    private boolean isFirstLaunch(Context context) {
        boolean isFirstLaunch = SharedPrefUtils.getStartDate(context) == 0L;
        if (isFirstLaunch) {
            SharedPrefUtils.setStartDate(context);
        }
        return isFirstLaunch;
    }

    private boolean isIgnoreDialog(Context context) {
        return SharedPrefUtils.shouldShowDialog(context);
    }

    private boolean isOverUseTime(Context context) {
        return SharedPrefUtils.getUseTime(context) > useUntilPrompt;
    }

    private boolean isOverDay(Context context) {
        long startDate = SharedPrefUtils.getStartDate(context);
        long today = new Date().getTime();

        long diff = today - startDate;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        return diffDays > dayUntilPrompt;
    }
}
