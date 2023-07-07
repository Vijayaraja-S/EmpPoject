package com.example.empmanage.employee.serviceImplimentation;

import com.example.empmanage.department.repository.DepartmentRepository;
import com.example.empmanage.department.entity.Department;
import com.example.empmanage.employee.DTO.EmployeeDepartmentDTO;
import com.example.empmanage.employee.DTO.UserDetailRequestDTO;
import com.example.empmanage.employee.entity.Employee;
import com.example.empmanage.employee.repository.EmployeeRepository;
import com.example.empmanage.employee.service.EmployeeService;
import com.example.empmanage.message.Message;
import com.example.empmanage.payslip.Repository.PaySlipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeImplimentation implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PaySlipRepository paySlipRepository;


    private DepartmentRepository departmentRepository;

    public EmployeeImplimentation(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<EmployeeDepartmentDTO> getAllEmployee() {

        List<EmployeeDepartmentDTO> employeeDepartmentDTOS = employeeRepository.findAll()
                .stream()
                .map(this::convertEntityTODTO)
                .collect(Collectors.toList());
        if (employeeDepartmentDTOS.isEmpty()) {
            throw new IllegalArgumentException("There is no employee in the dataBase ");
        }
        return employeeDepartmentDTOS;
    }


    private EmployeeDepartmentDTO convertEntityTODTO(Employee employee) {
        EmployeeDepartmentDTO edDto = new EmployeeDepartmentDTO();
        edDto.setEmpId(employee.getEmpId());
        edDto.setEmpName(employee.getEmpName());
        edDto.setDeptName(employee.getDepartment().getDeptName());
        edDto.setDeptCoordinator(employee.getDepartment().getDeptCoordinator());
        return edDto;
    }

    @Override
    public List<EmployeeDepartmentDTO> getEmployeesByDepartmentId(long departmentId) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new IllegalArgumentException("Department ID not present in the dataBase check ID  ");
        }
        List<EmployeeDepartmentDTO> employeeDepartmentDTOS = employeeRepository.findByDepartmentDeptId(departmentId)
                .stream()
                .map(this::convertEntityTODTO)
                .collect(Collectors.toList());
        if (employeeDepartmentDTOS.isEmpty()) {
            throw new IllegalArgumentException("There is no employee in the dataBase ");
        }
        return employeeDepartmentDTOS;
    }

    @Override
    public List<EmployeeDepartmentDTO> employeeFindBYID(long id) {
        List<EmployeeDepartmentDTO> employeeDepartmentDTOS = employeeRepository.findById(id)
                .stream()
                .map(this::convertEntityTODTO)
                .collect(Collectors.toList());
        if (employeeDepartmentDTOS.isEmpty()) {
            throw new IllegalArgumentException("Employee not present in the datbase ");
        }
        return employeeDepartmentDTOS;
    }


    @Override
    public String saveEmployee(UserDetailRequestDTO userDetailRequestDTO) {


        Department department = departmentRepository.findById(userDetailRequestDTO.getDepartmentID())
                .orElseThrow(() -> new IllegalArgumentException("Invalid department ID"));


        Employee employee = new Employee();
        employee.setEmpName(userDetailRequestDTO.getEmpName());
        employee.setEmail(userDetailRequestDTO.getEmail());
        employee.setJoiningDate(LocalDate.now());
        employee.setDepartment(department);
        employeeRepository.save(employee);

        return Message.formattedString;

    }

    @Override
    public String updateEmployee(UserDetailRequestDTO userDetailRequestDTO, Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new IllegalArgumentException("employee ID not present in the dataBase check ID" + id);
        }
        Department department = departmentRepository.findById(userDetailRequestDTO.getDepartmentID())
                .orElseThrow(() -> new IllegalArgumentException("Invalid department ID"));

        Employee employee = new Employee();
        employee.setEmpId(id);
        employee.setEmpName(userDetailRequestDTO.getEmpName());
        employee.setEmail(userDetailRequestDTO.getEmail());
        employee.setDepartment(department);
        employeeRepository.save(employee);
        return Message.formattedString1;
    }

    @Override
    public String deleteEmp(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new IllegalArgumentException("Invalid employee  ID: " + id);
        }
        employeeRepository.deleteById(id);
        return Message.deleteformate;
    }

    @Override
    public String saveListOfEmployees(MultipartFile file) {
        if (file.isEmpty())
            return "File is empty";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            List<Employee> employees = new ArrayList<>();
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                Employee employee = new Employee();
                String[] data = line.split(",");
                employee.setEmpName(data[0]);
                employee.setEmail(data[1]);

                Long departmentId = Long.parseLong(data[2]);
                Department department = departmentRepository.findById(departmentId)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid department ID"));

                employee.setDepartment(department);


                employees.add(employee);
            }
            employeeRepository.saveAll(employees);
            return "List Of Employee Add Successfully";

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> getBySalaryAndExperience(Double salary, long experience) {
        LocalDate joiningDateForExperience =LocalDate.now().minusYears(experience);
        List<Employee> employees=employeeRepository.findByPaySlips_BasicSalaryGreaterThanAndJoiningDateBefore(salary,joiningDateForExperience);
        if (employees.isEmpty())
            throw new IllegalArgumentException("DATA NOT FOUND ");
        return employees;
    }
}


