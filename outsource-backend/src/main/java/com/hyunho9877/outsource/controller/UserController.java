package com.hyunho9877.outsource.controller;

import com.hyunho9877.outsource.domain.ApplicationUser;
import com.hyunho9877.outsource.dto.ChatRoomDTO;
import com.hyunho9877.outsource.dto.UserDTO;
import com.hyunho9877.outsource.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/getUsers")
    public List<ApplicationUser> getUsers(@RequestBody UserDTO userDTO) {
        return userService.getUsers(userDTO.getId());
    }

    @PostMapping("/setProfileMessage")
    public String changeMessage(@RequestBody UserDTO userDTO) {
        assert !userDTO.getId().equals("");
        assert !userDTO.getNewMessage().equals("");

        log.info("new profile message received by {} : {}", userDTO.getId(), userDTO.getNewMessage());
        return userService.changeMessage(userDTO.getId(), userDTO.getNewMessage());
    }

    @PostMapping("/block")
    public ResponseEntity<?> block(@RequestBody ChatRoomDTO blockingInfo) {
        log.info("blocking requested : {}", blockingInfo);
        userService.block(blockingInfo.getId(), blockingInfo.getSubject());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/blocking/get")
    public ResponseEntity<List<String>> getBlockList(@RequestBody ChatRoomDTO user) {
        return ResponseEntity.ok(userService.getBlockList(user.getId()));
    }

    @GetMapping("/blocked/get")
    public ResponseEntity<List<String>> getBlockedList(@RequestBody ChatRoomDTO user) {
        return ResponseEntity.ok(userService.getBlockedList(user.getId()));
    }
}
