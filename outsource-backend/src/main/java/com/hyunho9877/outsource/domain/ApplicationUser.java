package com.hyunho9877.outsource.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
@Getter @Setter @ToString @Builder
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
    private String fcmToken;

    @PrePersist
    public void prePersist() {
        this.message = "";
        this.receiveNotification = true;
    }

    public ApplicationUser(String id, String nickName, String message) {
        this.id = id;
        this.nickName = nickName;
        this.message = message;
    }

    public static ApplicationUser getPublicProfile(ApplicationUser user) {
        return new ApplicationUser(user.getId(), user.getNickName(), user.getMessage());
    }
}
