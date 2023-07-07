package com.example.empmanage.employee.DTO;

import java.time.LocalDate;

public class UserDetailRequestDTO {

    private String empName;
    private String email;
    private LocalDate JoiningDate;
    private Long departmentID;

    public UserDetailRequestDTO() {
    }

    public UserDetailRequestDTO(String empName, String email, Long departmentID,LocalDate date) {
        this.empName = empName;
        this.email = email;
        this.departmentID = departmentID;
        this.JoiningDate =date;
    }

    public LocalDate getJoiningDate() {
        return JoiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.JoiningDate = joiningDate;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Long departmentID) {
        this.departmentID = departmentID;
    }

}