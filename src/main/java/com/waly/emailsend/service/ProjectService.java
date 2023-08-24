package com.waly.emailsend.service;

import com.waly.emailsend.dto.ProjectDTO;
import com.waly.emailsend.dto.TechnologyDTO;
import com.waly.emailsend.entities.Project;
import com.waly.emailsend.entities.Technology;
import com.waly.emailsend.repositories.ProjectRepository;
import com.waly.emailsend.repositories.TechnologiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private TechnologiesRepository technologiesRepository;


    @Transactional(readOnly = true)
    public List<ProjectDTO> findAll(){
        return repository.findAll().stream().map(ProjectDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ProjectDTO findById(long id){
        return new ProjectDTO(repository.findById(id).orElseThrow(()-> new RuntimeException("not found")));
    }

    @Transactional
    public ProjectDTO insert(ProjectDTO dto){
        Project entity = new Project();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProjectDTO(entity);
    }

    @Transactional
    public ProjectDTO update(ProjectDTO dto, long id){

        Project entity = repository.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProjectDTO(entity);
    }
    private Project copyDtoToEntity(ProjectDTO dto, Project entity){
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImgUrl(dto.getImgUrl());
        for (TechnologyDTO t : dto.getTechnologies()){
            Technology tec = technologiesRepository.getReferenceById(t.getId());
            entity.AddTechnology(tec);
        }
        return entity;
    }
}
