package com.example.empmanage.payslip.Service;

import com.example.empmanage.payslip.DTO.PaySlipRequestDTO;
import com.example.empmanage.payslip.DTO.PaySlipResponseDTO;
import com.example.empmanage.payslip.entity.PaySlip;

import java.util.List;
import java.util.Optional;

public interface PaySlipService {

    List<PaySlipResponseDTO> importantDetailsAll();
    List<PaySlipResponseDTO> importantDetailsByID(Long id);

    List<PaySlip> getAllPaySlipFullDetails();
    Optional<PaySlip> getFullDetailPaySlipByID(Long id);



    String savePayslip(PaySlipRequestDTO paySlipRequestDTO);

    String updatePaySlip(PaySlipRequestDTO paySlipRequestDTO ,Long id);

    String deletePaySlip(Long id);


    List<PaySlip> getPayslipsByEmployeeID(long employeeID);
}
