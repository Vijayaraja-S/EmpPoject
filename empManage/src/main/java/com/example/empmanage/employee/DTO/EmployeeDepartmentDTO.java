package com.example.empmanage.employee.DTO;

public class EmployeeDepartmentDTO {


    private  long empId;
    private String empName;
    private String deptName;
    private String deptCoordinator;

    public EmployeeDepartmentDTO() {
    }

    public EmployeeDepartmentDTO(long empId, String empName, String deptName, String deptCoordinator) {
        this.empId = empId;
        this.empName = empName;
        this.deptName = deptName;
        this.deptCoordinator = deptCoordinator;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
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
