package com.example.empmanage.department.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequestDTO {

    private  long deptId;
    private String deptName;
    private String deptCoordinator;

}
