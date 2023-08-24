package com.waly.emailsend.service;

import com.waly.emailsend.dto.EmailDTO;
import com.waly.emailsend.dto.UserDTO;
import com.waly.emailsend.dto.VerifyDTO;
import com.waly.emailsend.entities.User;
import com.waly.emailsend.entities.Verify;
import com.waly.emailsend.repositories.UserRepository;
import com.waly.emailsend.repositories.VerifyRepository;
import com.waly.emailsend.service.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Random;

@Service
public class UserSevice {

    @Autowired
    private UserRepository repository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private VerifyRepository verifyRepository;

    Random random = new Random();

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

        int randomNumber = 100_000 + random.nextInt(900_000);
        Verify verify = new Verify(entity.getId(), randomNumber, entity.getEmail(), Instant.now().plusSeconds(3600));
        verifyRepository.save(verify);
        emailService.sendEmailRecover(new EmailDTO(dto.getEmail()), String.valueOf(randomNumber));

        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO findByEmail(String email){
        return new UserDTO(repository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("not found")));
    }
    @Transactional
    public VerifyDTO findverify(){
        return new VerifyDTO(verifyRepository.getVerify(Instant.now(), 123321, "maria@gmail.com").get(0));
    }
    @Transactional
    public VerifyDTO verify(VerifyDTO dto){
        List<Verify> list = verifyRepository.getVerify(Instant.now(), dto.getCode(), dto.getEmail());
        System.out.println(dto.getCode() + dto.getEmail());

        if (list.isEmpty()) {
            return null;
        }
        User user = repository.findByEmail(dto.getEmail()).orElseThrow(()-> new ResourceNotFoundException("not found"));
        user.setVerified(true);
        repository.save(user);
        return new VerifyDTO(list.get(0));
    }
    @Transactional
    public UserDTO update(UserDTO dto) {
        User entity = new User();
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity = repository.save(entity);
        return new UserDTO(entity);
    }

}
