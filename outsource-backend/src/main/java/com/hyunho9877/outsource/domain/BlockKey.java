package com.hyunho9877.outsource.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor @AllArgsConstructor
public class BlockKey implements Serializable {
    private String requester;
    private String target;
}
