package com.waly.emailsend.controllers;

import com.waly.emailsend.dto.TechnologyDTO;
import com.waly.emailsend.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<TechnologyDTO> findById(@PathVariable long id){
        return ResponseEntity.ok(service.finById(id));
    }
    @PostMapping
    public ResponseEntity<TechnologyDTO> insert(@RequestBody TechnologyDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TechnologyDTO> update(@RequestBody TechnologyDTO dto, @PathVariable long id){
        dto = service.update(dto, id);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
