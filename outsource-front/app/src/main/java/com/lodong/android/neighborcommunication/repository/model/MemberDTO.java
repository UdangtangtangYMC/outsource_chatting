package com.lodong.android.neighborcommunication.repository.model;

public class MemberDTO {
    private String id;
    private String nickName;
    private String message;

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

    @Override
    public String toString() {
        return "MemberDTO{" +
                "id='" + id + '\'' +
                ", nickName='" + nickName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
