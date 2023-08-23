package com.waly.emailsend.controllers;

import com.waly.emailsend.dto.EmailDTO;
import com.waly.emailsend.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<EmailDTO> sendMail(@Valid @RequestBody EmailDTO dto){
        emailService.sendEmail(dto);
        return ResponseEntity.ok(dto);
    }
}
