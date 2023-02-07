package com.prgrms.be.app.domain.user.repository;

import com.prgrms.be.app.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
