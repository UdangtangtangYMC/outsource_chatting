package com.hyunho9877.outsource.service;

import com.hyunho9877.outsource.domain.ApplicationUser;
import com.hyunho9877.outsource.domain.BlockKey;
import com.hyunho9877.outsource.domain.Blocking;
import com.hyunho9877.outsource.repo.ApplicationUserRepository;
import com.hyunho9877.outsource.repo.BlockingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final ApplicationUserRepository userRepository;
    private final BlockingRepository blockingRepository;

    public List<ApplicationUser> getUsers(String username) {
        List<ApplicationUser> users = userRepository.findAll();
        Set<String> blocking = blockingRepository.findByRequester(username)
                .stream()
                .map(Blocking::getTarget)
                .collect(Collectors.toSet());

        return users.stream()
                .filter(Predicate.not(user->user.getId().equals(username)))
                .map(user -> ApplicationUser.getPublicProfile(user, blocking.contains(user.getId())))
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

    public void unblock(String id, String subject) {
        blockingRepository.deleteById(new BlockKey(id, subject));
    }

    public boolean isBlocked(String id, String subject) {
        return blockingRepository.existsById(new BlockKey(subject, id));
    }

    public List<String> getBlockList(String id) {
        return blockingRepository.findByRequester(id).stream().map(Blocking::getTarget).toList();
    }

    public List<String> getBlockedList(String id) {
        return blockingRepository.findByTarget(id).stream().map(Blocking::getRequester).toList();
    }

    @Transactional
    public void enableNotification(String id) {
        ApplicationUser applicationUser = userRepository.findById(id).orElseThrow();
        applicationUser.setReceiveNotification(true);
    }

    @Transactional
    public void disableNotification(String id) {
        ApplicationUser applicationUser = userRepository.findById(id).orElseThrow();
        applicationUser.setReceiveNotification(false);
    }
}
