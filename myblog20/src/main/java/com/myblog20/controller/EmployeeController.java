package com.myblog20.controller;

import com.myblog20.payload.EmployeeDto;
import com.myblog20.payload.EmployeeResponse;
import com.myblog20.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //http://localhost:8080/api/post

    @PostMapping
    public ResponseEntity<?> createEmployee(@Valid  @RequestBody EmployeeDto employeeDto, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        EmployeeDto Dto = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(Dto, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/post/1
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long id,@RequestBody EmployeeDto employeeDto){
        EmployeeDto dto = employeeService.updateEmployee(id, employeeDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    //http://localhost:8080/api/post/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee id deleted",HttpStatus.OK);
    }

    //http://localhost:8080/api/post/1
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("id") Long id){
        EmployeeDto Dto = employeeService.getEmployee(id);
        return new ResponseEntity<>(Dto,HttpStatus.OK);
    }

    //http://localhost:8080/api/post?pageNo=0&pageSize=5&sortBy=title&sortDir=asc
    @GetMapping
    public EmployeeResponse getAllEmployee(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int
                    pageNo,
            @RequestParam(value="pageSize",defaultValue = "5",required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String
                    sortDir
    ){
        EmployeeResponse allEmployee = employeeService.getAllEmployee(pageNo, pageSize,sortBy,sortDir);
        return allEmployee;
    }
}
