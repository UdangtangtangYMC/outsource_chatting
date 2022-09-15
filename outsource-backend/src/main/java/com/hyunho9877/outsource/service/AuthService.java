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

    public boolean auth(ApplicationUser user) {
        ApplicationUser applicationUser = userRepository.findById(user.getId()).orElseThrow();
        return applicationUser.getPassword().equals(user.getPassword());
    }

    private boolean isDuplicated(String username) {
        return userRepository.findById(username).isPresent();
    }

    private void registerNewRabbitMQAccount(String username, boolean durability) {
        rabbitMQManager.declareQueue(username, durability);
    }
}
