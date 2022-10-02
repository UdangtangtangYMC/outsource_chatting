package com.lodong.android.neighborcommunication.utils.preferences;

public enum UserInfo {
    ID("id"), NickName("nickName"), IsLogIn("isLogIn"), StatusMessage("message"), Notification("notification"), TOKEN("token");

    private final String name;

    UserInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
