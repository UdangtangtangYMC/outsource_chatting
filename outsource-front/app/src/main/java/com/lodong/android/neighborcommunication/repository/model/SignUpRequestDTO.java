package com.lodong.android.neighborcommunication.repository.model;

import com.google.gson.annotations.SerializedName;

public class SignUpRequestDTO {
    @SerializedName("status")
    private int status;

    public int getStatus() {
        return status;
    }
}
