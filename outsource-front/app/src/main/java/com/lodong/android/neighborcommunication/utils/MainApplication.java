package com.lodong.android.neighborcommunication.utils;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.lodong.android.neighborcommunication.utils.stomp.StompUtils;

public class MainApplication extends Application {
    private static final String TAG = MainApplication.class.getSimpleName();
    private static MainApplication mInstance;
    private BroadcastReceiver connectionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectMgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo mobNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo wifiNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
                // unconnect network
                Log.i(TAG, "unconnect");
            } else {
                // connect network
                Log.i(TAG, "network change");
                connectStomp();
            }
        }
    };

    IntentFilter intentFilter = new IntentFilter();


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static MainApplication getInstance() {
        return mInstance;
    }

   /* public void observeNetWork() {
        ConnectivityManager connectivityManager = getInstance().getSystemService(ConnectivityManager.class);
        connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(Network network) {
                Log.e(TAG, "The default network is now: " + network);
               *//* if (!StompUtils.INSTANCE.isConnect()) {
                    Log.d(TAG, "stomp is connect : " + StompUtils.INSTANCE.isConnect());
                    reconnectStomp();
                }*//*
            }

            @Override
            public void onLost(Network network) {
                Log.e(TAG, "The application no longer has a default network. The last default network was " + network);
            }

            @Override
            public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                Log.e(TAG, "The default network changed capabilities: " + networkCapabilities);
            }

            @Override
            public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
                Log.e(TAG, "The default network changed link properties: " + linkProperties);
            }
        });
    }
*/
    public void connectStomp() {
        StompUtils.init(getApplicationContext());
    }

    public void registerBroadCast(){
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(connectionReceiver, intentFilter);
    }
}
