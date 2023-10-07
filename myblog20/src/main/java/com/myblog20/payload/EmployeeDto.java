package com.myblog20.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class EmployeeDto {
    private Long id;

    @NotEmpty
    @Size(min=2,message ="Employee title Should be atleast 2 character")
    private String title;

    @NotEmpty
    @Size(min=4,message ="Employee description Should be atleast 4 character")
    private String description;

    @NotEmpty
    @Size(min=5,message ="Employee content Should be atleast 5 character")
    private String content;
}
