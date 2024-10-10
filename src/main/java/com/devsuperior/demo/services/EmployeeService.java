package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EmployeeDTO;
import com.devsuperior.demo.entities.Department;
import com.devsuperior.demo.entities.Employee;
import com.devsuperior.demo.repositories.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }


    @Transactional(readOnly = true)
    public Page<EmployeeDTO> findAllPaged(Pageable pageable) {
        Page<Employee> list = repository.findAll(pageable);
        return list.map(EmployeeDTO::new);
    }

    // save an employee
    @Transactional
    public EmployeeDTO save(EmployeeDTO dto) {
        Employee entity = new Employee();
        BeanUtils.copyProperties(dto, entity);
        entity.setDepartment(new Department(dto.departmentId(), null));
        entity = repository.save(entity);
        return new EmployeeDTO(entity);
    }


}
