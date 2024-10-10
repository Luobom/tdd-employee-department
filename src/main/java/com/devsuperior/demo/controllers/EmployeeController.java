package com.devsuperior.demo.controllers;

import com.devsuperior.demo.dto.EmployeeDTO;
import com.devsuperior.demo.services.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeDTO>> findAllPaged(Pageable pageable) {
        Page<EmployeeDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> insert(@RequestBody EmployeeDTO dto) {
        dto = service.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
