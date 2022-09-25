package com.lodong.android.neighborcommunication.utils.preferences;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.lodong.android.neighborcommunication.R;
import com.lodong.android.neighborcommunication.data.UserInfo;
import com.lodong.android.neighborcommunication.repository.Repository;
import com.lodong.android.neighborcommunication.repository.RepositoryImpl;
import com.lodong.android.neighborcommunication.repository.model.ChatDisplayDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;
import com.lodong.android.neighborcommunication.view.MainActivity;
import com.lodong.android.neighborcommunication.view.chatroom.ChatRoomActivity;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class FCMService extends FirebaseMessagingService{
    private final String TAG = FCMService.class.getSimpleName();
    private final String channelID = "CHATTING";
    private final String channelName = "CHATTING CHANNEL";
    private final String description = "THIS IS CHATTING CHANNEL";
    private Repository repository;
    private static final Gson gson = new Gson();


    public FCMService() {
        repository = RepositoryImpl.getInstance();
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

        }
        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        handleMessage(remoteMessage);

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @Override
    public void onMessageSent(@NonNull String msgId) {
        super.onMessageSent(msgId);
    }

    @Override
    public void onSendError(@NonNull String msgId, @NonNull Exception exception) {
        super.onSendError(msgId, exception);
    }

    @Override
    public void onNewToken(@NonNull String token) {
        Log.d(TAG, "Refreshed token : " + token);
    }

    @Override
    protected Intent getStartCommandIntent(Intent originalIntent) {
        return super.getStartCommandIntent(originalIntent);
    }

    @Override
    public void handleIntent(Intent intent) {
        super.handleIntent(intent);
    }

    public void handleMessage(RemoteMessage remoteMessage){
        Intent intent = new Intent(this, ChatRoomActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //title이 보내는 사람
        //body 가 메시지
        Map<String, String> messageHashMap = remoteMessage.getData();
        String dto = messageHashMap.get("data");
        ChatMessageDTO chatMessage = gson.fromJson(dto, ChatMessageDTO.class);
        Log.d(TAG, "data : " + chatMessage.toString());
        if (!repository.isChatRoomExists(chatMessage.getSender(), UserInfo.getInstance().getId()))
            repository.insertChatRoom(new ChatRoomDTO(chatMessage.getRoomId(), chatMessage.getSender(), chatMessage.getReceiver(), chatMessage.getSenderNickName(), chatMessage.getReceiverNickName()));
        if (chatMessage.getSender().equals(UserInfo.getInstance().getId())) chatMessage.setViewType(Code.ViewType.RIGHT_CONTENT);
        else chatMessage.setViewType(Code.ViewType.LEFT_CONTENT);
        repository.insertChatMessage(chatMessage);
        repository.insertChatDisplay(new ChatDisplayDTO(chatMessage.getRoomId(), chatMessage.getChatId()));

        long chatRoomId = chatMessage.getRoomId();
        String receiver = chatMessage.getSender();
        String receiverNickName = chatMessage.getSenderNickName();
        intent.putExtra("chatRoomId", chatRoomId);
        intent.putExtra("receiver", receiver);
        intent.putExtra("receiverNickName", receiverNickName);
        Log.d(TAG, "chatRoomId" + chatRoomId);
        Log.d(TAG, "receiver" + receiver);
        Log.d(TAG, "receiverNickName" + receiverNickName);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT|PendingIntent.FLAG_MUTABLE);

        //알림 관련
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 100, 200});
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        //알림 사운드 관련
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(remoteMessage.getData().get("title"))
                .setContentText(remoteMessage.getData().get("body"))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(333 /* ID of notification */, notificationBuilder.build());

    }
}