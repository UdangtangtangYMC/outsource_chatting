package com.hyunho9877.outsource.service;

import com.hyunho9877.outsource.domain.ApplicationUser;
import com.hyunho9877.outsource.repo.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ApplicationUserRepository userRepository;

    public List<ApplicationUser> getUsers(String username) {
        return userRepository.findAll().stream()
                .filter(user->!user.getId().equals(username))
                .map(ApplicationUser::getPublicProfile)
                .toList();
    }

    @Transactional
    public String changeMessage(String userId, String newMessage) {
        ApplicationUser applicationUser = userRepository.findById(userId).orElseThrow();
        applicationUser.setMessage(newMessage);
        return newMessage;
    }

}
