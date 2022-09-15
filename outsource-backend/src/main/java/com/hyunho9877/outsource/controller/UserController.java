package com.hyunho9877.outsource.controller;

import com.hyunho9877.outsource.domain.ApplicationUser;
import com.hyunho9877.outsource.dto.UserDTO;
import com.hyunho9877.outsource.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/getUsers")
    public List<ApplicationUser> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/setProfileMessage")
    public String changeMessage(@RequestBody UserDTO userDTO) {
        assert !userDTO.getId().equals("");
        assert !userDTO.getNewMessage().equals("");

        return userService.changeMessage(userDTO.getId(), userDTO.getNewMessage());
    }

}
