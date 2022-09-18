package com.hyunho9877.outsource.social;

import com.hyunho9877.outsource.domain.ApplicationUser;
import com.hyunho9877.outsource.repo.ApplicationUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

@SpringBootTest
public class AuthTest {
    @Autowired
    ApplicationUserRepository userRepository;

    @Test
    @Description("블랙리스트 추가")
    void addBlackList() {
        ApplicationUser applicationUser = userRepository.findById("test").orElseThrow();
        ApplicationUser blockTarget = userRepository.findById("id").orElseThrow();
        applicationUser.getBlockingList().add(blockTarget);
    }
}
