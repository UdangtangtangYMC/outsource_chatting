package com.hyunho9877.outsource.service;

import com.hyunho9877.outsource.domain.ApplicationUser;
import com.hyunho9877.outsource.repo.ApplicationUserRepository;
import com.hyunho9877.outsource.utils.rabbitmq.RabbitMQManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ApplicationUserRepository userRepository;
    private final RabbitMQManager rabbitMQManager;
    private final PasswordEncoder passwordEncoder;

    public void register(ApplicationUser user) {
        if(isDuplicated(user.getId())) throw new IllegalStateException();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        ApplicationUser savedUser = userRepository.save(user);
        registerNewRabbitMQAccount(savedUser.getId(), true);
    }

    public ApplicationUser auth(ApplicationUser user) {
        ApplicationUser applicationUser = userRepository.findById(user.getId()).orElseThrow();
        if(applicationUser.getPassword().equals(user.getPassword())){
            return ApplicationUser.getPublicProfile(applicationUser, false);
        } else {
            return null;
        }
    }

    private boolean isDuplicated(String username) {
        return userRepository.existsById(username);
    }

    private void registerNewRabbitMQAccount(String username, boolean durability) {
        rabbitMQManager.declareQueue(username, durability);
    }

    public void signOut(ApplicationUser user) {
        ApplicationUser applicationUser = userRepository.findById(user.getId()).orElseThrow();
        applicationUser.setFcmToken(null);
    }

    @Transactional
    public void registerFCMToken(ApplicationUser user) {
        ApplicationUser applicationUser = userRepository.findById(user.getId()).orElseThrow();
        applicationUser.setFcmToken(user.getFcmToken());
    }

}
