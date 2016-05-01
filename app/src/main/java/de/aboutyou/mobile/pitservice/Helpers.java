package de.aboutyou.mobile.pitservice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class Helpers {
    public static void finishActivity(Context context) {
        Intent intent = new Intent();
        intent.setAction(Constants.FINISH_ACTION);
        context.sendBroadcast(intent);
    }

    // Returns true if device has been tracked, otherwise false
    public static boolean isTracked(Context context) {
        SharedPreferences settings = context.getSharedPreferences("settings", 0);
        return settings.getBoolean("tracked", false);
    }

    // Set to true if device was tracked successfully
    public static void setTracked(boolean tracked, Context context) {
        SharedPreferences settings = context.getSharedPreferences("settings", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("tracked", tracked);
        editor.apply();
    }
}
