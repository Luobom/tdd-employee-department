package com.devsuperior.demo.dto;

import com.devsuperior.demo.entities.Department;

import java.util.List;

public record DepartmentDTO(
        Long id,
        String name,
        List<EmployeeDTO> employees

) {

    public DepartmentDTO(Department entity) {
        this(entity.getId(), entity.getName(), entity.getEmployees().stream().map(EmployeeDTO::new).toList());
    }

}
