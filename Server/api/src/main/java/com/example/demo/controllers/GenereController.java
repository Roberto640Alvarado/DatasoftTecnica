package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.entities.Generes;
import com.example.demo.services.GeneresService;


@RestController
@RequestMapping("/generes")
@CrossOrigin("*")
public class GenereController {
	
	@Autowired
	private GeneresService generesService;
	
	@GetMapping("/all")
	public ResponseEntity<?> generesAll(){
		List<Generes> generes = generesService.getAll();
		if(generes != null) {
			return ResponseEntity.ok(generes);	
		}else {
			return ResponseEntity.notFound().build();
		}
	}

}
