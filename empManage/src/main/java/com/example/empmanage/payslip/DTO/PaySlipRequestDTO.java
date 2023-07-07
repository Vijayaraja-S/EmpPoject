package com.example.empmanage.payslip.DTO;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PaySlipRequestDTO {

    private String month;
    private int year;
    private double basicSalary;
    private double allowance;
    private double deduction;
    private double netSalary;
    private  Long  empId;

}
