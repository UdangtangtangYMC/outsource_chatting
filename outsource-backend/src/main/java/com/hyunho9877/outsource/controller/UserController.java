package com.hyunho9877.outsource.controller;

import com.hyunho9877.outsource.domain.ApplicationUser;
import com.hyunho9877.outsource.dto.UserDTO;
import com.hyunho9877.outsource.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        return userService.changeMessage(userDTO.getId(), userDTO.getNewMessage());
    }

}
