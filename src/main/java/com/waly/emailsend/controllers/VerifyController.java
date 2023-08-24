package com.waly.emailsend.controllers;

import com.waly.emailsend.dto.UserDTO;
import com.waly.emailsend.dto.VerifyDTO;
import com.waly.emailsend.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "verify")
public class VerifyController {

    @Autowired
    private OauthService service;

    @PostMapping
    public ResponseEntity<VerifyDTO> verify(@RequestBody VerifyDTO dto){
        dto = service.verify(dto);
        if (dto == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}
