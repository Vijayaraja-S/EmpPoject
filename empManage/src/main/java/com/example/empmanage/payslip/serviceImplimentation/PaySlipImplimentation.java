package com.example.empmanage.payslip.serviceImplimentation;

import com.example.empmanage.payslip.DTO.PaySlipRequestDTO;
import com.example.empmanage.payslip.DTO.PaySlipResponseDTO;
import com.example.empmanage.employee.repository.EmployeeRepository;
import com.example.empmanage.payslip.Repository.PaySlipRepository;
import com.example.empmanage.employee.entity.Employee;
import com.example.empmanage.payslip.Service.PaySlipService;
import com.example.empmanage.payslip.entity.PaySlip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaySlipImplimentation implements PaySlipService {
    @Autowired
     private PaySlipRepository paySlipRepository;
    @Autowired
     private EmployeeRepository employeeRepository;



    @Override
    public List<PaySlipResponseDTO> importantDetailsAll() {

        List<PaySlipResponseDTO> paySlipResponseDTOS= paySlipRepository.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
        if (paySlipResponseDTOS.isEmpty()) {
            throw new IllegalArgumentException("There are no payslips in the database");
        }

        return paySlipResponseDTOS;
    }

    private PaySlipResponseDTO convertEntityToDTO(PaySlip paySlip){
        PaySlipResponseDTO paySlipResDTO= new PaySlipResponseDTO();
        paySlipResDTO.setMonth(paySlip.getMonth());
        paySlipResDTO.setYear(paySlip.getYear());
        paySlipResDTO.setPayslipId(paySlip.getPayslipId());
        paySlipResDTO.setBasicSalary(paySlip.getBasicSalary());
        paySlipResDTO.setAllowance(paySlip.getAllowance());
        return paySlipResDTO;
    }

    @Override
    public List<PaySlipResponseDTO> importantDetailsByID(Long id) {
        List<PaySlipResponseDTO> paySlipResponseDTOS= paySlipRepository.findById(id)
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
        if (paySlipResponseDTOS.isEmpty()){
            throw new IllegalArgumentException("Invalid paySlip  ID: " + id);
        }
        return  paySlipResponseDTOS;
    }

    @Override
    public List<PaySlip> getAllPaySlipFullDetails() {
        List<PaySlip> paySlips=paySlipRepository.findAll();
        if (paySlips.isEmpty()){
            throw new IllegalArgumentException("There is no payslips in the DataBase");
        }
        return paySlips;
    }

    @Override
    public Optional<PaySlip> getFullDetailPaySlipByID(Long id) {
        return Optional.ofNullable(paySlipRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Payslip ID")));
    }

    @Override
    public String savePayslip(PaySlipRequestDTO paySlipRequestDTO) {

        Employee employee =employeeRepository.findById(paySlipRequestDTO.getEmpId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Employee ID"));

        PaySlip paySlip=new PaySlip();
        paySlip.setMonth(paySlipRequestDTO.getMonth());
        paySlip.setYear(paySlipRequestDTO.getYear());
        paySlip.setBasicSalary(paySlipRequestDTO.getBasicSalary());
        paySlip.setAllowance(paySlipRequestDTO.getAllowance());
        paySlip.setDeduction(paySlipRequestDTO.getDeduction());
        paySlip.setNetSalary(paySlipRequestDTO.getNetSalary());
        paySlip.setEmployee(employee);
        paySlipRepository.save(paySlip);
        return "PaySlip created Successfully";
    }

    @Override
    public String updatePaySlip(PaySlipRequestDTO paySlipRequestDTO,Long id ) {
        if (!paySlipRepository.existsById(id)){
            throw new IllegalArgumentException("There is no particular payslip Id check ID");
        }
        Employee employee =employeeRepository.findById(paySlipRequestDTO.getEmpId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Employee ID"));

        PaySlip paySlip=new PaySlip();
        paySlip.setPayslipId(id);
        paySlip.setMonth(paySlipRequestDTO.getMonth());
        paySlip.setYear(paySlipRequestDTO.getYear());
        paySlip.setBasicSalary(paySlipRequestDTO.getBasicSalary());
        paySlip.setAllowance(paySlipRequestDTO.getAllowance());
        paySlip.setDeduction(paySlipRequestDTO.getDeduction());
        paySlip.setNetSalary(paySlipRequestDTO.getNetSalary());
        paySlip.setEmployee(employee);

         paySlipRepository.save(paySlip);
         String str ="updated successfully";
         return str;
    }

    @Override
    public String deletePaySlip(Long id) {
        if (!paySlipRepository.existsById(id)) {
            throw new IllegalArgumentException("Invalid Payslip ID: " + id);
        }
        paySlipRepository.deleteById(id);
        String str= "Deleted Successfully";


        return str;
    }

    @Override
    public List<PaySlip> getPayslipsByEmployeeID(long employeeID) {


        List<PaySlip> paySlips=paySlipRepository.findByEmployeeId(employeeID);
        if(paySlips.isEmpty()){
            throw new IllegalArgumentException("Invalid employee  ID: " + employeeID);
        }
        return paySlips;
    }


}
