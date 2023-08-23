package com.waly.emailsend.service;

import com.waly.emailsend.dto.ProjectDTO;
import com.waly.emailsend.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;


    @Transactional(readOnly = true)
    public List<ProjectDTO> findAll(){
        return repository.findAll().stream().map(ProjectDTO::new).toList();
    }
}
