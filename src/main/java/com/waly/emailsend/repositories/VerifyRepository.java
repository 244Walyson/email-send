package com.waly.emailsend.repositories;

import com.waly.emailsend.entities.User;
import com.waly.emailsend.entities.Verify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface VerifyRepository extends JpaRepository<Verify, Long> {

    @Query("SELECT obj FROM Verify obj WHERE obj.email = :email AND obj.code = :code AND obj.expiration > :now")
    List<Verify> getVerify(Instant now, Integer code, String email);
}
