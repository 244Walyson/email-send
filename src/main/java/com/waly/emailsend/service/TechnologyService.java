package com.waly.emailsend.service;

import com.waly.emailsend.dto.TechnologyDTO;
import com.waly.emailsend.repositories.TechnologiesRepository;
import com.waly.emailsend.service.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
