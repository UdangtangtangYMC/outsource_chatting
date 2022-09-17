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
    private final String TAG = StompUtils.class.getSimpleName();
    private StompClient stompClient;
    private final String BASE_URL = "ws://49.174.169.48:13883/ws";

    public void connect(Context context) {
        stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, BASE_URL);
        String subscribeURL = "/queue/" + getId(context);
        Log.d(TAG, subscribeURL);
        Disposable subscribe = stompClient.topic(subscribeURL).subscribe(lifecycleEvent -> {
            Log.d(TAG, lifecycleEvent.getStompCommand());
            String payload = lifecycleEvent.getPayload();
            Log.d(TAG, payload);
        });
        stompClient.connect();
    }

    private String getId(Context context) {
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

    public boolean isConnected() {
        return stompClient.isConnected();
    }
}
