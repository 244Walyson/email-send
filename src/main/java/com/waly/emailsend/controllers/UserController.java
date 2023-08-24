package com.waly.emailsend.controllers;

import com.waly.emailsend.dto.UserDTO;
import com.waly.emailsend.dto.VerifyDTO;
import com.waly.emailsend.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserSevice sevice;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok(sevice.findAll());
    }
    @GetMapping(value = "/email")
    public ResponseEntity<UserDTO> findByEmail(@RequestParam(value = "email", defaultValue = "") String email){
        return ResponseEntity.ok(sevice.findByEmail(email));
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO dto){
        dto = sevice.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    @PostMapping(value = "/verify")
    public ResponseEntity<VerifyDTO> verify(@RequestBody VerifyDTO dto){
        dto = sevice.verify(dto);
        if (dto == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/findverify")
    public ResponseEntity<VerifyDTO> findverify(){
        return ResponseEntity.ok(sevice.findverify());
    }
}
