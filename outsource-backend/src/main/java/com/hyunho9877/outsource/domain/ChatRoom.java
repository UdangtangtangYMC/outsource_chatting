package com.hyunho9877.outsource.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    @ManyToOne
    private ApplicationUser roomUserOne;
    @ManyToOne
    private ApplicationUser roomUserTwo;
}
