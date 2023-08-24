package com.waly.emailsend.service;

import com.waly.emailsend.dto.UserDTO;
import com.waly.emailsend.entities.User;
import com.waly.emailsend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserSevice {

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public List<UserDTO> findAll(){
        return repository.findAll().stream().map(UserDTO::new).toList();
    }

    @Transactional
    public UserDTO insert(UserDTO dto) {
        User entity = new User();
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity = repository.save(entity);
        return new UserDTO(entity);
    }

}
