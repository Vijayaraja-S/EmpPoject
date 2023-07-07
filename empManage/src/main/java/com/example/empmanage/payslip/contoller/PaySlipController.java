package com.example.empmanage.payslip.contoller;

import com.example.empmanage.payslip.DTO.PaySlipRequestDTO;
import com.example.empmanage.payslip.DTO.PaySlipResponseDTO;
import com.example.empmanage.payslip.Service.PaySlipService;
import com.example.empmanage.payslip.entity.PaySlip;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/payslip")
public class PaySlipController {

    PaySlipService paySlipService;

    public PaySlipController(PaySlipService paySlipService) {
        this.paySlipService = paySlipService;
    }

    @GetMapping
    public List<PaySlipResponseDTO> importantDetailsAll() {
        return paySlipService. importantDetailsAll();
    }
    @GetMapping("/{id}")
    public List<PaySlipResponseDTO> importantDetailsAllByID(@PathVariable("id") Long id ) {
        return paySlipService.importantDetailsByID(id);
    }

    @GetMapping({"/all"})
    public List<PaySlip> getAllPaySlipFullDetails() {
        return paySlipService.getAllPaySlipFullDetails();
    }

    @GetMapping({"all/{id}"})
    public Optional<PaySlip> getFullDetailPaySlipByID(@PathVariable("id") Long id) {
        return paySlipService.getFullDetailPaySlipByID(id);
    }

    @GetMapping("/employee/{employeeID}")
    public List<PaySlip> getPayslipsByEmployeeID(@PathVariable long employeeID) {
        return paySlipService.getPayslipsByEmployeeID(employeeID);
    }

    @PutMapping("/{id}")
    public String updatePaySlip(@PathVariable("id") Long id,@RequestBody PaySlipRequestDTO paySlipRequestDTO) {
        String str =paySlipService.updatePaySlip(paySlipRequestDTO,id);
        return str;
    }

    @PostMapping
    public String savePayslip(@RequestBody PaySlipRequestDTO paySlipRequestDTO){

        String str1= paySlipService.savePayslip(paySlipRequestDTO);
        return str1;
    }

    @DeleteMapping("/{id}")
    public String deletePayslip(@PathVariable ("id")Long id){
        String Str=paySlipService.deletePaySlip(id);
        return  Str;
    }
}
