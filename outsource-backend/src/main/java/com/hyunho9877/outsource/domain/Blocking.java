package com.hyunho9877.outsource.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@Getter
@IdClass(BlockKey.class)
@AllArgsConstructor @NoArgsConstructor
public class Blocking {
    @Id
    private String requester;
    @Id
    private String target;
}
