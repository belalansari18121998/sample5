package com.myblog20.service.impl;

import com.myblog20.entity.Employee;
import com.myblog20.exception.ResourceNotFound;
import com.myblog20.payload.EmployeeDto;
import com.myblog20.payload.EmployeeResponse;
import com.myblog20.repository.EmployeeRepository;
import com.myblog20.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = mapToEntity(employeeDto);
        Employee save = employeeRepository.save(employee);

        EmployeeDto Dto = mapToDto(save);


        return Dto;
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Employee not Found:" + id)
        );
        employee.setId(employeeDto.getId());
        employee.setTitle(employeeDto.getTitle());
        employee.setDescription(employeeDto.getDescription());
        employee.setContent(employeeDto.getContent());

        Employee save = employeeRepository.save(employee);
        EmployeeDto dto = mapToDto(save);
        return dto;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeDto getEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Employee Not Found:" + id)
        );
        EmployeeDto Dto = mapToDto(employee);
        return Dto;
    }

    @Override
    public  EmployeeResponse getAllEmployee(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();

        Pageable pageable= PageRequest.of(pageNo,pageSize, sort);
        Page<Employee> pageEmployee = employeeRepository.findAll(pageable);
        List<Employee> employeeList=pageEmployee.getContent();
        List<EmployeeDto> collectDto = employeeList.stream().map(employees -> mapToDto(employees)).collect(Collectors.toList());

        EmployeeResponse employeeResponse=new EmployeeResponse();

        employeeResponse.setEmployeeDtoList(collectDto);
        employeeResponse.setPageNo(pageEmployee.getNumber());
        employeeResponse.setPageSize(pageEmployee.getSize());
        employeeResponse.setTotalElement(pageEmployee.getTotalElements());
        employeeResponse.setTotalPages(pageEmployee.getTotalPages());
        employeeResponse.setLast(pageEmployee.isLast());

        return employeeResponse;
    }


    public Employee  mapToEntity(EmployeeDto employeeDto){
        Employee employee=new Employee();
        employee.setId(employeeDto.getId());
        employee.setTitle(employeeDto.getTitle());
        employee.setDescription(employeeDto.getDescription());
        employee.setContent(employeeDto.getContent());
        return employee;
    }

    public EmployeeDto mapToDto(Employee employee){
        EmployeeDto employeeDto=new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setTitle(employee.getTitle());
        employeeDto.setDescription(employee.getDescription());
        employeeDto.setContent(employee.getContent());
        return employeeDto;
    }
}
