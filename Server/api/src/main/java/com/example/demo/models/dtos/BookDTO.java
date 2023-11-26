package com.example.demo.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BookDTO {
	
    private Integer id;
	
	@NotEmpty
    private String name;
	
	@NotEmpty
    private String summary;
	
	@NotEmpty
    private Float price;
	
	@NotEmpty
    private Boolean state;
	
	@NotEmpty
    private String image;
	

}
