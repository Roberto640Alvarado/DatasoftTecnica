package com.example.demo.models.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.demo.models.entities.Token;

@Data
@NoArgsConstructor
public class TokenDTO {
	private String token;

	public TokenDTO(Token token) {
		this.token = token.getContent();
	}
}