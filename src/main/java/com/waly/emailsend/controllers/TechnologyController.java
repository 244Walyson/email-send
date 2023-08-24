package com.waly.emailsend.controllers;

import com.waly.emailsend.dto.TechnologyDTO;
import com.waly.emailsend.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/technologies")
public class TechnologyController {

    @Autowired
    private TechnologyService service;

    @GetMapping
    public ResponseEntity<List<TechnologyDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
}
