package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.entities.Generes;


public interface GenereRepository extends JpaRepository<Generes, Integer> {
	
	public Generes findOneById(Integer id);
	

}
