package com.hyunho9877.outsource.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ChatRoomDTO {
    private String id;
    private String subject;

    public ChatRoomDTO(String id) {
        this.id = id;
    }
}
