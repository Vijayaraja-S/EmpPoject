package com.example.empmanage.payslip.Repository;


import com.example.empmanage.employee.entity.Employee;
import com.example.empmanage.payslip.entity.PaySlip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaySlipRepository extends JpaRepository<PaySlip,Long> {


    @Query("SELECT p FROM PaySlip p WHERE p.employee.empId = :employeeId")
    List<PaySlip> findByEmployeeId(Long employeeId);




}
