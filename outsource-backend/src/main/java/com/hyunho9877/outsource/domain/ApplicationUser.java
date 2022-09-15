package com.hyunho9877.outsource.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
@Builder
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class ApplicationUser {
    @Id
    private String id;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String nickName;
    @Column(nullable = false)
    private String message;
    private boolean receiveNotification;

    @PrePersist
    public void prePersist() {
        this.message = "";
        this.receiveNotification = true;
    }
}
