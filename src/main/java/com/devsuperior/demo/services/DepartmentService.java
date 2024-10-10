package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.DepartmentDTO;
import com.devsuperior.demo.entities.Department;
import com.devsuperior.demo.repositories.DepartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }



    @Transactional(readOnly = true)
    public Page<DepartmentDTO> findAllPaged(Pageable pageable) {
        Page<Department> list = repository.findAll(pageable);
        return list.map(DepartmentDTO::new);
    }

}
