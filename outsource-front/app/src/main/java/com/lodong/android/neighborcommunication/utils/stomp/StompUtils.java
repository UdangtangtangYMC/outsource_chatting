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

import java.util.Optional;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
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
        String subscribeURL = "/queue/" + getId(context);
        Log.d(TAG, subscribeURL);
        stompClient.reconnect();
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
        stompClient.lifecycle().subscribe(lifecycleEvent -> {
            switch (lifecycleEvent.getType()){
                case ERROR:
                    Log.e(TAG, "lifecycleEvent: "+lifecycleEvent.getException().toString());
                    break;
            }
        });
    }

    private static String getId(Context context) {
        return PreferenceManager.getId(context);
    }

    public void disconnect() {
        stompClient.disconnect();
    }

    public void send(ChatMessageDTO message, GetChatRoomIdCallBack getChatRoomIdCallBack) {
        if (!repository.isChatRoomExists(message.getSender(), message.getReceiver())) {
            repository.setRoomCreatedCallBack(getRoomCreateCallBack(getChatRoomIdCallBack));
            repository.createChatRoom(message.getSender(), message.getReceiver(), message);
        } else {
            String toJson = gson.toJson(message);
            stompClient.send("/pub/msg", toJson)
                    .subscribeOn(Schedulers.io())
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.w(TAG, "~~~~ onCompleted " + d.toString());
                        }

                        @Override
                        public void onComplete() {
                            Log.w(TAG, "~~~~ onCompleted ");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.w(TAG, "~~~~ onCompleted "+e.getMessage());
                        }

                    });
        }
    }

    public RoomCreateCallBack getRoomCreateCallBack(GetChatRoomIdCallBack getChatRoomIdCallBack) {
        return new RoomCreateCallBack() {
            @Override
            public void onSuccess(ChatRoomDTO chatRoom, ChatMessageDTO message) {
                Log.d(TAG, "chatRoom : " + chatRoom.toString());
                Log.d(TAG, "chatMessage : " + message.toString());
                message.setRoomId(chatRoom.getRoomId());
                getChatRoomIdCallBack.onSuccess(chatRoom.getRoomId());
                repository.insertChatRoom(chatRoom);
                String toJson = new Gson().toJson(message);
                stompClient.send("/pub/msg", toJson).subscribe();
            }

            @Override
            public void onFailed(ChatRoomDTO chatRoom, ChatMessageDTO message) {
                Log.d(TAG, "chatRoom : " + chatRoom.toString());
                Log.d(TAG, "chatMessage : " + message.toString());
                message.setRoomId(chatRoom.getRoomId());
                getChatRoomIdCallBack.onSuccess(chatRoom.getRoomId());
                repository.insertChatRoom(chatRoom);
                String toJson = new Gson().toJson(message);
                stompClient.send("/pub/msg", toJson).subscribe();
            }
        };
    }

    public boolean isConnect() {
        return stompClient.isConnected();
    }

    public void reConnect() {
        stompClient.reconnect();
    }

  /*  public void reconnect() {
        Log.d(TAG, "stompClient.isConnected() : " + stompClient.isConnected());
        stompClient.reconnect();
        Log.d(TAG, "stompClient.isConnected() : " + stompClient.isConnected());
    }*/

    public static StompClient getStompClient() {
        return stompClient;
    }

    public static Optional<String> getStompId(Context context) {
        try {
            return Optional.ofNullable(stompClient.getTopicId("/queue/" + getId(context)));
        } catch (NullPointerException e) {
            Log.d(TAG, e.toString());
            return Optional.empty();
        }
    }
}
