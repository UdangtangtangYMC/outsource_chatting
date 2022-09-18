package com.hyunho9877.outsource.service;

import com.hyunho9877.outsource.domain.ApplicationUser;
import com.hyunho9877.outsource.domain.Blocking;
import com.hyunho9877.outsource.repo.ApplicationUserRepository;
import com.hyunho9877.outsource.repo.BlockingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ApplicationUserRepository userRepository;
    private final BlockingRepository blockingRepository;

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

    @Transactional
    public void block(String id, String subject) {
        ApplicationUser requester = userRepository.getReferenceById(id);
        ApplicationUser blockTarget = userRepository.getReferenceById(subject);
        blockingRepository.save(new Blocking(requester.getId(), blockTarget.getId()));
    }

    public List<String> getBlockList(String id) {
        return blockingRepository.findByRequester(id).stream().map(Blocking::getTarget).toList();
    }

    public List<String> getBlockedList(String id) {
        return blockingRepository.findByTarget(id).stream().map(Blocking::getRequester).toList();
    }
}
