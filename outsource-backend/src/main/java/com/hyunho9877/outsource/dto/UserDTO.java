package com.hyunho9877.outsource.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String id;
    private String password;
    private String nickName;
    private String newMessage;
    private String fcmToken;
}
