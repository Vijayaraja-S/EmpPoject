package com.example.empmanage.employee.service;

import com.example.empmanage.employee.DTO.EmployeeDepartmentDTO;
import com.example.empmanage.employee.DTO.UserDetailRequestDTO;
import com.example.empmanage.employee.entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDepartmentDTO> getAllEmployee();

    List<EmployeeDepartmentDTO> getEmployeesByDepartmentId(long departmentId);

    List<EmployeeDepartmentDTO> employeeFindBYID(long id);


    String saveEmployee(UserDetailRequestDTO userDetailRequestDTO);

    String updateEmployee(UserDetailRequestDTO userDetailRequestDTO,Long id);

    String deleteEmp(Long id);


     String saveListOfEmployees(MultipartFile file);

    List<Employee> getBySalaryAndExperience(Double salary, long experience);
}




