package com.hyunho9877.outsource.domain;

import lombok.*;

import javax.persistence.*;

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
    @Transient
    private boolean isBlocked;

    @PrePersist
    public void prePersist() {
        this.message = "";
        this.receiveNotification = true;
    }

    public ApplicationUser(String id, String nickName, String message, boolean isBlocked, boolean receiveNotification) {
        this.id = id;
        this.nickName = nickName;
        this.message = message;
        this.isBlocked = isBlocked;
        this.receiveNotification = receiveNotification;
    }

    public static ApplicationUser getPublicProfile(ApplicationUser user, boolean isBlocked) {
        return new ApplicationUser(user.getId(), user.getNickName(), user.getMessage(), isBlocked, user.isReceiveNotification());
    }
}
