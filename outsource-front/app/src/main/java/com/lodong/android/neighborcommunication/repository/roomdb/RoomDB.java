package com.lodong.android.neighborcommunication.repository.roomdb;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.lodong.android.neighborcommunication.repository.chatmessageservice.ChatMessageDao;
import com.lodong.android.neighborcommunication.repository.chatroomservice.ChatRoomDao;
import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ChatMessageDTO.class, ChatRoomDTO.class}, version = 4, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static final String TAG = RoomDB.class.getSimpleName();
    private static String DATABASE_NAME = "communication";
    private static RoomDB instance;

    public static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriterExcutor
            = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public synchronized static RoomDB getInstance(Context context){
        Log.d(TAG, "roomdb create");
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract ChatRoomDao chatRoomDao();

    public abstract ChatMessageDao chatMessageDao();

}
