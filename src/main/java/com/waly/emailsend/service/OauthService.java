package com.waly.emailsend.service;

import com.waly.emailsend.dto.VerifyDTO;
import com.waly.emailsend.entities.User;
import com.waly.emailsend.entities.Verify;
import com.waly.emailsend.repositories.UserRepository;
import com.waly.emailsend.repositories.VerifyRepository;
import com.waly.emailsend.service.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class OauthService {

    @Autowired
    private VerifyRepository verifyRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public VerifyDTO verify(VerifyDTO dto){
        List<Verify> list = verifyRepository.getVerify(Instant.now(), dto.getCode(), dto.getEmail());
        System.out.println(dto.getCode() + dto.getEmail());

        if (list.isEmpty()) {
            return null;
        }
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(()-> new ResourceNotFoundException("not found"));
        user.setVerified(true);
        userRepository.save(user);
        return new VerifyDTO(list.get(0));
    }
}
