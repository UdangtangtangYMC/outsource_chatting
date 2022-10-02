package com.hyunho9877.outsource.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Subscription {
    private String subscriptionID;
    private String sessionID;
}
