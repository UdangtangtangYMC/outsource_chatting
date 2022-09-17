package com.lodong.android.neighborcommunication.utils.preferences;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessaging;
import com.lodong.android.neighborcommunication.view.callback.GetFCMToken;

public class FCMManager {
    private static final String TAG = FCMManager.class.getSimpleName();

    public static void getNowFCMToken(GetFCMToken getFCMToken){
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                        return;
                    }

                    // Get new FCM registration token
                    String token = task.getResult();

                    Log.d(TAG, "nowToken : " + token);

                });
    }
}
