package com.hyunho9877.outsource.service;

import com.hyunho9877.outsource.domain.ApplicationUser;
import com.hyunho9877.outsource.repo.ApplicationUserRepository;
import com.hyunho9877.outsource.utils.rabbitmq.RabbitMQManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ApplicationUserRepository userRepository;
    private final RabbitMQManager rabbitMQManager;

    public void register(ApplicationUser user) {
        if(isDuplicated(user.getId())) throw new IllegalStateException();
        ApplicationUser savedUser = userRepository.save(user);
        registerNewRabbitMQAccount(savedUser.getId(), true);
    }

    public ApplicationUser auth(ApplicationUser user) {
        ApplicationUser applicationUser = userRepository.findById(user.getId()).orElseThrow();
        if(applicationUser.getPassword().equals(user.getPassword())){
            return ApplicationUser.getPublicProfile(applicationUser);
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
}
