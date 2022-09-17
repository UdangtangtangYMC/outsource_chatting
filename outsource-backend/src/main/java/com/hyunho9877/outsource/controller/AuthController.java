package com.hyunho9877.outsource.controller;

import com.hyunho9877.outsource.domain.ApplicationUser;
import com.hyunho9877.outsource.dto.UserDTO;
import com.hyunho9877.outsource.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDTO> registration(@RequestBody UserDTO user) {
        log.info("user data received {}", user);

        ApplicationUser applicationUser = ApplicationUser.builder()
                .id(user.getId())
                .password(user.getPassword())
                .nickName(user.getNickName())
                .fcmToken(user.getFcmToken())
                .build();

        authService.register(applicationUser);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/do")
    public ResponseEntity<?> auth(@RequestBody UserDTO user) {
        log.info("user data received {}", user);

        assert !user.getId().equals("");
        assert !user.getPassword().equals("");

        ApplicationUser applicationUser = ApplicationUser.builder()
                .id(user.getId())
                .password(user.getPassword())
                .nickName(user.getNickName())
                .build();

        ApplicationUser authResult = authService.auth(applicationUser);
        if(authResult != null) return ResponseEntity.ok(authResult);
        else return ResponseEntity.badRequest().body(applicationUser.getId());
    }

    @PostMapping("/out")
    public ResponseEntity<?> signOut(@RequestBody UserDTO user) {
        log.info("user data received {}", user);

        ApplicationUser applicationUser = ApplicationUser.builder()
                .id(user.getId())
                .password(user.getPassword())
                .nickName(user.getNickName())
                .build();

        authService.signOut(applicationUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/fcm")
    public ResponseEntity<?> registerFCMToken(@RequestBody UserDTO userDTO) {

        ApplicationUser user = ApplicationUser.builder()
                .id(userDTO.getId())
                .fcmToken(userDTO.getFcmToken())
                .build();
        log.info("fcm received by {} : {}", userDTO.getId(), userDTO.getFcmToken());
        authService.registerFCMToken(user);

        return ResponseEntity.ok().build();
    }
}
