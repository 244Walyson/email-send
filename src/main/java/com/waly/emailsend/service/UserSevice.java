package com.waly.emailsend.service;

import com.waly.emailsend.dto.UserDTO;
import com.waly.emailsend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserSevice {

    @Autowired
    private UserRepository repository;

    @Transactional
    public List<UserDTO> findAll(){
        return repository.findAll().stream().map(UserDTO::new).toList();
    }
}
