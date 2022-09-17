package com.lodong.android.neighborcommunication.view.callback;

public interface GetFCMToken {
    public void onSuccess(String fcmToken);
    public void onFailed(Exception e);
}
