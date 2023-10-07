package com.myblog20.service;

import com.myblog20.payload.EmployeeDto;
import com.myblog20.payload.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);

    void deleteEmployee(Long id);

    EmployeeDto getEmployee(Long id);

    EmployeeResponse getAllEmployee(int pageNo, int pageSize, String sortBy, String sortDir);
}
