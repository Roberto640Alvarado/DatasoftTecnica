package com.example.demo.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class GenereDTO {
	
	@NotEmpty
    private Integer id;
	
	@NotEmpty
    private String name;

}
