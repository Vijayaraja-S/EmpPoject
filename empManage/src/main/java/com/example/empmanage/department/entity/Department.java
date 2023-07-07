package com.example.empmanage.department.entity;

import com.example.empmanage.employee.entity.Employee;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "department", uniqueConstraints = @UniqueConstraint(columnNames = "deptId"))
public class Department {
    @Id
    private  long deptId;
    private String deptName;
    private String deptCoordinator;
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Employee> employees;

    public Department() {
    }

    public Department(long deptId, String deptName, String deptCoordinator) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.deptCoordinator = deptCoordinator;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public long getDeptId() {
        return deptId;
    }

    public void setDeptId(long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCoordinator() {
        return deptCoordinator;
    }

    public void setDeptCoordinator(String deptCoordinator) {
        this.deptCoordinator = deptCoordinator;
    }

}
