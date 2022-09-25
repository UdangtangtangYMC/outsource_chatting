package com.lodong.android.neighborcommunication.repository.login;

import com.google.gson.JsonObject;
import com.lodong.android.neighborcommunication.data.UserInfo;
import com.lodong.android.neighborcommunication.repository.retrofit.RetrofitService;
import com.lodong.android.neighborcommunication.repository.signup.SignUpService;
import com.lodong.android.neighborcommunication.view.callback.GetLogInResultCallBack;
import com.lodong.android.neighborcommunication.view.callback.NotificationCallBack;

public class LoginService {
    private final String TAG = SignUpService.class.getSimpleName();
    private RetrofitService retrofitService;
    private GetLogInResultCallBack logInResultCallBack;
    private NotificationCallBack notificationCallBack;

    public LoginService(){
        retrofitService = RetrofitService.getInstance();
    }

    public void login(JsonObject jsonObject){
        retrofitService.getLoginResult(jsonObject, logInResultCallBack);
    }

    public void setLoginResultCallBack(GetLogInResultCallBack callBack) {
        this.logInResultCallBack = callBack;
    }

    public void setNotificationCallBack(NotificationCallBack callBack) {
        this.notificationCallBack = callBack;
    }

    public void enablePushNotification() {
        String id = UserInfo.getInstance().getId();
        retrofitService.enablePushNotification(id, notificationCallBack);
    }

    public void disablePushNotification() {
        String id = UserInfo.getInstance().getId();
        retrofitService.disablePushNotification(id, notificationCallBack);
    }
}
