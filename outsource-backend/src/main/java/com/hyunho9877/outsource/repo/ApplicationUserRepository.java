package com.hyunho9877.outsource.repo;

import com.hyunho9877.outsource.domain.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, String> {
}
