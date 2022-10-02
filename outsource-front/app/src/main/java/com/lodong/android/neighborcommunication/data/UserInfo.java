package com.lodong.android.neighborcommunication.data;

public class UserInfo {
    private static UserInfo instance;
    private String id;
    private String nickName;
    private String message;

    private UserInfo(){}

    public static UserInfo getInstance() {
        if(instance == null){
            instance = new UserInfo();
        }
        return instance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
