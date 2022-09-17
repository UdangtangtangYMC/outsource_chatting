package com.lodong.android.neighborcommunication.utils.stomp;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.lodong.android.neighborcommunication.repository.Repository;
import com.lodong.android.neighborcommunication.repository.RepositoryImpl;
import com.lodong.android.neighborcommunication.repository.model.ChatMessage;
import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;
import com.lodong.android.neighborcommunication.utils.preferences.PreferenceManager;

import io.reactivex.disposables.Disposable;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;

public class StompUtils {
    private static final String TAG = StompUtils.class.getSimpleName();
    private static final String BASE_URL = "ws://49.174.169.48:13883/ws";
    public static final StompUtils INSTANCE = new StompUtils();
    private static final Gson gson = new Gson();
    private static final Repository repository = RepositoryImpl.getInstance();
    private static final StompClient stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, BASE_URL);

    public static void init(Context context) {
        String subscribeURL = "/queue/" + getId(context);
        Log.d(TAG, subscribeURL);
        Disposable subscribe = stompClient.topic(subscribeURL).subscribe(lifecycleEvent -> {
            String payload = lifecycleEvent.getPayload();
            ChatMessageDTO message = gson.fromJson(payload, ChatMessageDTO.class);
            if(!repository.isChatRoomExists(message.getSender(), message.getReceiver())) repository.insertChatRoom(new ChatRoomDTO(message.getRoom(), message.getSender(), message.getReceiver()));
            repository.insertChatMessage(message);
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
        String toJson = gson.toJson(message);
        stompClient.send("/pub/hello", toJson).subscribe();
    }
}
