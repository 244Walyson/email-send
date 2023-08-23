package com.waly.emailsend.repositories;

import com.waly.emailsend.entities.Project;
import com.waly.emailsend.entities.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologiesRepository extends JpaRepository<Technology, Long> {
}
