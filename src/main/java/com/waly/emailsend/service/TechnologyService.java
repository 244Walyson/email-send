package com.waly.emailsend.service;

import com.waly.emailsend.dto.TechnologyDTO;
import com.waly.emailsend.entities.Technology;
import com.waly.emailsend.repositories.TechnologiesRepository;
import com.waly.emailsend.service.Exceptions.DatabaseException;
import com.waly.emailsend.service.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TechnologyService {

    @Autowired
    private TechnologiesRepository repository;

    @Transactional
    public List<TechnologyDTO> findAll(){
        return repository.findAll().stream().map(TechnologyDTO::new).toList();
    }

    @Transactional
    public TechnologyDTO finById(long id) {
        return new TechnologyDTO(repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("not found")));
    }

    @Transactional
    public TechnologyDTO insert(TechnologyDTO dto){
        Technology entity = new Technology();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new TechnologyDTO(entity);
    }

    @Transactional
    public TechnologyDTO update(TechnologyDTO dto, long id){
        Technology entity = repository.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new TechnologyDTO(entity);
    }

    private Technology copyDtoToEntity(TechnologyDTO dto, Technology entity) {
        entity.setName(dto.getName());
        entity.setImgUrl(dto.getImgUrl());
        return entity;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(long id) {
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("not found");
        }
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("data integrity violation");
        }
    }
}
