package com.example.demo.services;


import java.util.List;

import com.example.demo.models.dtos.ActiveBookDTO;
import com.example.demo.models.dtos.SaveBookDTO;
import com.example.demo.models.entities.Books;

public interface  BookService {
	
	Books findOneById(Integer id);
	Books findOneByName(String title);
	public List<Books> getAll();
	public List<Books> getByGenre(String genre);
	void updateActiveBooks(Integer id, ActiveBookDTO state) throws Exception;
	void updateCreatedBooks(Integer id, SaveBookDTO book) throws Exception;
	public List <Books> searchByName(String name);

}
