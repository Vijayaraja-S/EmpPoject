package com.example.empmanage.payslip.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PaySlipResponseDTO {
    private Long PayslipId;
    private String month;
    private int year;
    private double basicSalary;
    private double allowance;
}
