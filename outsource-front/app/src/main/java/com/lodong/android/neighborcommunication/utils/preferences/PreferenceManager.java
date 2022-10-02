package com.lodong.android.neighborcommunication.utils.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

    private static final String PREFERENCES_NAME = "app_preferences";

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static void setId(Context context, String email) {
        SharedPreferences preferences = getPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserInfo.ID.getName(), email);
        editor.apply();
    }

    public static void setNotificationEnabled(Context context, boolean notification) {
        SharedPreferences preferences = getPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(UserInfo.Notification.getName(), notification);
        editor.apply();
    }

    public static void setStatusMessage(Context context, String statusMessage) {
        SharedPreferences preferences = getPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserInfo.StatusMessage.getName(), statusMessage);
        editor.apply();
    }

    public static void setIsLogin(Context context, boolean isLogin) {
        SharedPreferences preferences = getPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(UserInfo.IsLogIn.getName(), isLogin);
        editor.apply();
    }

    public static void setNickName(Context context, String nickName) {
        SharedPreferences preferences = getPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserInfo.NickName.getName(), nickName);
        editor.apply();
    }

    public static void setToken(Context context, String token){
        SharedPreferences preferences = getPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserInfo.TOKEN.getName(), token);
        editor.apply();
    }

    public static String getId(Context context) {
        SharedPreferences preferences = getPreferences(context);
        return preferences.getString(UserInfo.ID.getName(), null);
    }

    public static String getStatusMessage(Context context) {
        SharedPreferences preferences = getPreferences(context);
        return preferences.getString(UserInfo.StatusMessage.getName(), null);
    }

    public static String getNickName(Context context) {
        SharedPreferences preferences = getPreferences(context);
        return preferences.getString(UserInfo.NickName.getName(), null);
    }

    public static String getToken(Context context){
        SharedPreferences preferences = getPreferences(context);
        return preferences.getString(UserInfo.TOKEN.getName(), null);
    }

    public static boolean isLogin(Context context) {
        SharedPreferences preferences = getPreferences(context);
        return preferences.getBoolean(UserInfo.IsLogIn.getName(), false);
    }

    public static boolean isNotificationEnabled(Context context) {
        SharedPreferences preferences = getPreferences(context);
        return preferences.getBoolean(UserInfo.Notification.getName(), false);
    }


}
