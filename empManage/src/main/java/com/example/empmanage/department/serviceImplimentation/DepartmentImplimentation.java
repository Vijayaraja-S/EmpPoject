package com.example.empmanage.department.serviceImplimentation;

import com.example.empmanage.department.DTO.DepartmentRequestDTO;
import com.example.empmanage.department.repository.DepartmentRepository;
import com.example.empmanage.department.service.DepartmentService;
import com.example.empmanage.department.entity.Department;
import com.example.empmanage.employee.entity.Employee;
import com.example.empmanage.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class DepartmentImplimentation implements DepartmentService {

    private EmployeeRepository employeeRepository;



    private DepartmentRepository departmentRepository;

    public DepartmentImplimentation(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAllDepartment() {
        List<Department> department=departmentRepository.findAll();
        if (department.isEmpty()){
            throw new IllegalArgumentException("There is no department in database ");
        }
        return department;
    }

    @Override
    public Optional<Department> departmentFindByID(long id) {

        if (!departmentRepository.existsById(id)){
            throw new IllegalArgumentException("There is no particular department check the ID ");
        }
        return departmentRepository.findById(id);
    }



    @Override
    public String saveDepartment( DepartmentRequestDTO departmentRequestDTO) {

       Department department=new Department();
       department.setDeptId(departmentRequestDTO.getDeptId());
       department.setDeptName(departmentRequestDTO.getDeptName());
       department.setDeptCoordinator(departmentRequestDTO.getDeptCoordinator());
        departmentRepository.save(department);
        return "Department details save Successfully ";
    }

    @Override
    public String updateDepartment(Long id,DepartmentRequestDTO departmentRequestDTO) {
        departmentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Invalid Department ID"));
        Department department1 = new Department();
        department1.setDeptId(id);
        department1.setDeptName(departmentRequestDTO.getDeptName());
        department1.setDeptCoordinator(departmentRequestDTO.getDeptCoordinator());
         departmentRepository.save(department1);
         return  "Department Update Successfully ";
    }

    @Override
    public String deleteDepartment(Long id) {
        if (!departmentRepository.existsById(id)){
            throw  new IllegalArgumentException("The id is not present in the dataBase ");
        }
        departmentRepository.deleteById(id);
         return "Deleted Successfully";
    }
}
