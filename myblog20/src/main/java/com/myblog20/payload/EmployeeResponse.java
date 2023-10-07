package com.myblog20.payload;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeResponse {
    private List<EmployeeDto> employeeDtoList;
    private int pageNo;
    private int pageSize;
    private long totalElement;
    private int totalPages;
    private boolean last;
}
