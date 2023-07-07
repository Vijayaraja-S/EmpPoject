package com.example.empmanage.department.service;

import com.example.empmanage.department.DTO.DepartmentRequestDTO;
import com.example.empmanage.department.entity.Department;
import com.example.empmanage.employee.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> getAllDepartment();

    Optional<Department> departmentFindByID(long id );
    String saveDepartment ( DepartmentRequestDTO departmentRequestDTO);

    String deleteDepartment(Long id);



   String updateDepartment(Long id, DepartmentRequestDTO departmentRequestDTO);
}
