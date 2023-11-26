package com.example.demo.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SaveBookDTO {
	
	@NotEmpty
    private String name;
	
	@NotEmpty
    private String summary;
	
	@NotEmpty
    private Float price;
	
	@NotEmpty
    private String image;

}
