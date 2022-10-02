package com.lodong.android.neighborcommunication.utils.stomp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.lodong.android.neighborcommunication.repository.Repository;
import com.lodong.android.neighborcommunication.repository.RepositoryImpl;
import com.lodong.android.neighborcommunication.repository.model.ChatDisplayDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;
import com.lodong.android.neighborcommunication.utils.preferences.Code;
import com.lodong.android.neighborcommunication.utils.preferences.PreferenceManager;
import com.lodong.android.neighborcommunication.view.callback.GetChatRoomIdCallBack;
import com.lodong.android.neighborcommunication.view.callback.RoomCreateCallBack;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;

public class StompUtils {
    private static final String TAG = StompUtils.class.getSimpleName();
    private static final String BASE_URL = "ws://210.99.223.38:13884/ws";
    public static final StompUtils INSTANCE = new StompUtils();
    private static final Gson gson = new Gson();
    private static final Repository repository = RepositoryImpl.getInstance();
    private static final StompClient stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, BASE_URL);

    @SuppressLint("CheckResult")
    public static void init(Context context) {
        if(getId(context) != null){
            String subscribeURL = "/queue/" + getId(context);
            Log.d(TAG, subscribeURL);
            stompClient.connect();
            stompClient
                    .topic(subscribeURL)
                    .subscribe(data -> {
                        String payload = data.getPayload();
                        ChatMessageDTO message = gson.fromJson(payload, ChatMessageDTO.class);
                        Log.d(TAG, "init message : " + message.toString());
                        if (!repository.isChatRoomExists(message.getSender(), message.getReceiver()))
                            Log.d(TAG, "create chatting room");
                        repository.insertChatRoom(new ChatRoomDTO(message.getRoomId(), message.getSender(), message.getReceiver(), message.getSenderNickName(), message.getReceiverNickName()));
                        if (message.getSender().equals(getId(context)))
                            message.setViewType(Code.ViewType.RIGHT_CONTENT);
                        else message.setViewType(Code.ViewType.LEFT_CONTENT);
                        Log.d(TAG, "insert chatmessage");
                        repository.insertChatMessage(message);
                        Log.d(TAG, "insert chatDisplay");
                        repository.insertChatDisplay(new ChatDisplayDTO(message.getRoomId(), message.getChatId()));
                    }, throwable -> {
                        Log.d(TAG, "데이터 수신 오류 : " + throwable.getMessage());
                    });
        }else{
            Log.e(TAG, "id is null");
        }
    }

    private static String getId(Context context) {
        return PreferenceManager.getId(context);
    }

    public static void disconnect() {
        stompClient.disconnect();
    }

    @SuppressLint("CheckResult")
    public void send(ChatMessageDTO message, GetChatRoomIdCallBack getChatRoomIdCallBack) {
        if (!repository.isChatRoomExists(message.getSender(), message.getReceiver())) {
            repository.setRoomCreatedCallBack(getRoomCreateCallBack(getChatRoomIdCallBack));
            repository.createChatRoom(message.getSender(), message.getReceiver(), message);
        } else {
            String toJson = gson.toJson(message);
            stompClient.send("/pub/msg", toJson)
                    .subscribe(() -> {
                        message.setViewType(Code.ViewType.RIGHT_CONTENT);
                        repository.insertChatMessage(message);
                    });
        }
    }

    public RoomCreateCallBack getRoomCreateCallBack(GetChatRoomIdCallBack getChatRoomIdCallBack) {
        return new RoomCreateCallBack() {
            @SuppressLint("CheckResult")
            @Override
            public void onSuccess(ChatRoomDTO chatRoom, ChatMessageDTO message) {
                Log.d(TAG, "chatRoom : " + chatRoom.toString());
                Log.d(TAG, "chatMessage : " + message.toString());
                message.setRoomId(chatRoom.getRoomId());
                getChatRoomIdCallBack.onSuccess(chatRoom.getRoomId());
                repository.insertChatRoom(chatRoom);
                String toJson = new Gson().toJson(message);
                stompClient.send("/pub/msg", toJson) .subscribe(() -> {
                    message.setViewType(Code.ViewType.RIGHT_CONTENT);
                    repository.insertChatMessage(message);
                    Log.d(TAG, "sent data");
                }, throwable -> {
                    Log.d(TAG, throwable.getMessage());
                });
            }

            @Override
            public void onFailed(ChatRoomDTO chatRoom, ChatMessageDTO message) {
                Log.d(TAG, "chatRoom : " + chatRoom.toString());
                Log.d(TAG, "chatMessage : " + message.toString());
                message.setRoomId(chatRoom.getRoomId());
                getChatRoomIdCallBack.onSuccess(chatRoom.getRoomId());
                repository.insertChatRoom(chatRoom);
                String toJson = new Gson().toJson(message);
                stompClient.send("/pub/msg", toJson)
                        .subscribe(() -> {
                            message.setViewType(Code.ViewType.RIGHT_CONTENT);
                            repository.insertChatMessage(message);
                        });
            }
        };
    }

    public boolean isConnect(){
        return stompClient.isConnected();
    }

    public static void reconnect() {
        stompClient.reconnect();
    }

    public static StompClient getStompClient() {
        return stompClient;
    }
}
