package com.example.demo.services;


import java.util.List;

import com.example.demo.models.entities.Generes;

public interface GeneresService {
	
	public List<Generes> getAll();
	Generes findOneById(Integer id);

}
