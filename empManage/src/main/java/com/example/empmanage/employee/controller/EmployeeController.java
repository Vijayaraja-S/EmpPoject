package com.example.empmanage.employee.controller;

import com.example.empmanage.employee.DTO.EmployeeDepartmentDTO;
import com.example.empmanage.employee.service.EmployeeService;
import com.example.empmanage.employee.DTO.UserDetailRequestDTO;
import com.example.empmanage.employee.entity.Employee;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public  List<EmployeeDepartmentDTO> findAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping({"/{id}"})
    public List<EmployeeDepartmentDTO> findEmpId(@PathVariable("id") Long id) {
        return employeeService.employeeFindBYID(id);
    }

    @GetMapping("/department/{departmentId}")
    public List<EmployeeDepartmentDTO> getEmployeesByDepartmentId(@PathVariable long departmentId) {
        return employeeService.getEmployeesByDepartmentId(departmentId);
    }

    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable("id") Long id ,@RequestBody UserDetailRequestDTO userDetailRequestDTO ) {
        String result = employeeService.updateEmployee(userDetailRequestDTO,id);
        return result;
    }

    @PostMapping
    public String createEmployee(@RequestBody UserDetailRequestDTO userDetailRequestDTO) {
        String result =employeeService.saveEmployee(userDetailRequestDTO);
        return result;
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable ("id")Long id){
       String str= employeeService.deleteEmp(id);
        return str;
    }
    @PostMapping("/list/employee")
    public String listOfEmployees(@RequestParam ("file") MultipartFile file){
        String str =employeeService.saveListOfEmployees(file);
        return  str ;
    }
    @GetMapping("/salary-and-experience/{sal}/{exp}")
    public  List<Employee> listOFEmployeeBySalaryAndExperience(@PathVariable("sal")Double salary,@PathVariable ("exp")long experience) {

        return  employeeService.getBySalaryAndExperience(salary,experience);
    }
}
