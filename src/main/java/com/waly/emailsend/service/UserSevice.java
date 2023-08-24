package com.waly.emailsend.service;

import com.waly.emailsend.config.projections.UserDetailsProjection;
import com.waly.emailsend.dto.EmailDTO;
import com.waly.emailsend.dto.UserDTO;
import com.waly.emailsend.dto.VerifyDTO;
import com.waly.emailsend.entities.Role;
import com.waly.emailsend.entities.User;
import com.waly.emailsend.entities.Verify;
import com.waly.emailsend.repositories.UserRepository;
import com.waly.emailsend.repositories.VerifyRepository;
import com.waly.emailsend.service.Exceptions.DatabaseException;
import com.waly.emailsend.service.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Random;

@Service
public class UserSevice implements UserDetailsService {

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
    public UserDTO update(UserDTO dto, long id) {
        User entity = repository.getReferenceById(id);
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity = repository.save(entity);
        return new UserDTO(entity);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("not found");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("data integrity violation");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);

        if (result.isEmpty()){
            throw new UsernameNotFoundException("email not found");
        }

        User user = new User();
        user.setEmail(result.get(0).getUsername());
        user.setPassword(result.get(0).getPassword());
        for (UserDetailsProjection projection : result){
            user.AddRoles(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        return user;
    }
}
