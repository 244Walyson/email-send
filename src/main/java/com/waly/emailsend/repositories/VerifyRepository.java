package com.waly.emailsend.repositories;

import com.waly.emailsend.entities.User;
import com.waly.emailsend.entities.Verify;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerifyRepository extends JpaRepository<Verify, Long> {
}
