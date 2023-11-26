package com.example.demo.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDTO {
	
	
    private Integer id;
	
	@NotEmpty
    private String fullName;
	
	@NotEmpty
    private String username;
	
	@NotEmpty
    private String password;
	
	
    private Boolean state;

}
