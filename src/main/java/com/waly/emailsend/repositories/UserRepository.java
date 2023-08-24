package com.waly.emailsend.repositories;

import com.waly.emailsend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
