package com.example.demo.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ActiveBookDTO {
	@NotEmpty
	private String name;
	private Boolean state;
}
