package com.example.empmanage.department.controller;

import com.example.empmanage.department.DTO.DepartmentRequestDTO;
import com.example.empmanage.department.entity.Department;
import com.example.empmanage.department.service.DepartmentService;
import com.example.empmanage.employee.entity.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> findAllDepartment() {
        return departmentService.getAllDepartment();
    }

    @GetMapping({"/{id}"})
    public Optional<Department> findDeptId(@PathVariable("id") Long id) {
        return departmentService.departmentFindByID(id);
    }

    @PutMapping("/{id}")
    public String updateDepartment(@PathVariable ("id")Long id,@RequestBody DepartmentRequestDTO departmentRequestDTO) {
        String str= departmentService.updateDepartment(id,departmentRequestDTO);
        return str;
    }

    @PostMapping
    public String createDepartment(@RequestBody DepartmentRequestDTO departmentRequestDTO) {
        String str=departmentService.saveDepartment( departmentRequestDTO);
        return str;

    }



    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable ("id")Long id) {
        String str = departmentService.deleteDepartment(id);
        return str;
    }

}
