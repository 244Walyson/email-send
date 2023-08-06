package com.waly.emailsend.controllers;

import com.waly.emailsend.dto.EmailDTO;
import com.waly.emailsend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<EmailDTO> sendMail(@RequestBody EmailDTO dto){
        emailService.sendEmail(dto);
        return ResponseEntity.ok(dto);
    }
}
