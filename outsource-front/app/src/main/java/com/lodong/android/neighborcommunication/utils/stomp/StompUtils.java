package com.lodong.android.neighborcommunication.utils.stomp;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.lodong.android.neighborcommunication.repository.model.ChatMessage;
import com.lodong.android.neighborcommunication.utils.preferences.PreferenceManager;

import io.reactivex.disposables.Disposable;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;

public class StompUtils {
    private static final String TAG = StompUtils.class.getSimpleName();
    private static final String BASE_URL = "ws://49.174.169.48:13883/ws";
    public static final StompUtils INSTANCE = new StompUtils();
    private static final StompClient stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, BASE_URL);

    private StompUtils() {

    }

    public static void init(Context context) {
        String subscribeURL = "/queue/" + getId(context);
        Log.d(TAG, subscribeURL);
        Disposable subscribe = stompClient.topic(subscribeURL).subscribe(lifecycleEvent -> {
            Log.d(TAG, lifecycleEvent.getStompCommand());
            String payload = lifecycleEvent.getPayload();
            Log.d(TAG, payload);
        });
        stompClient.connect();
    }

    private static String getId(Context context) {
        return PreferenceManager.getId(context);
    }

    public void disconnect() {
        stompClient.disconnect();
    }

    public void send(ChatMessage message) {
        Log.d(TAG, message.toString());
        Gson gson = new Gson();
        String toJson = gson.toJson(message);
        stompClient.send("/pub/hello", toJson).subscribe();
    }
}
