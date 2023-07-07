package com.example.empmanage.employee.repository;

import com.example.empmanage.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findByDepartmentDeptId(long departmentId);


    List<Employee> findByPaySlips_BasicSalaryGreaterThanAndJoiningDateBefore(double salary, LocalDate joiningDate);


}
