package com.waly.emailsend.repositories;

import com.waly.emailsend.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
