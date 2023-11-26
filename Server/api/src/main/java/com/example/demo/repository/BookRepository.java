package com.example.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.entities.Books;

public interface BookRepository extends JpaRepository<Books, Integer>{
	
	public Books findOneById(Integer id);
	public Books findOneByName(String name);
	List<Books> findByGeneresName(String genre);
	List<Books> findByNameContainingIgnoreCase(String name);
}
