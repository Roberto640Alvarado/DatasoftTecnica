package com.example.demo.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.dtos.ActiveBookDTO;
import com.example.demo.models.dtos.SaveBookDTO;
import com.example.demo.models.entities.Books;
import com.example.demo.models.entities.Generes;
import com.example.demo.repository.BookRepository;
import com.example.demo.services.BookService;
import com.example.demo.services.GeneresService;

import jakarta.transaction.Transactional;


@Service
public class BooksServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;
	


	@Override
	public Books findOneById(Integer id) {
		try {
			return bookRepository.findById(id)
					.orElse(null);
		} catch (Exception e) {
			return null;
	    }
    }

	@Override
	public Books findOneByName(String name) {
		return bookRepository.findOneByName(name);
	}

	@Override
	public List<Books> getAll() {
		return bookRepository.findAll();
	}

	@Override
	public void updateActiveBooks(Integer id, ActiveBookDTO state) throws Exception {
		Optional<Books> eventOptional = bookRepository.findById(id);
		if (eventOptional.isPresent()) {
			Books book = eventOptional.get();
			book.setState(state.getState());
			bookRepository.save(book);
		}else {
			throw new Exception("Event not found");
		}
		
	}

	@Override
	public List<Books> getByGenre(String genre) {
	    return bookRepository.findByGeneresName(genre);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void updateCreatedBooks(Integer id,SaveBookDTO info) throws Exception {
		Books newBook = findOneById(id);
		newBook.setName(info.getName());
		newBook.setSummary(info.getSummary());
		newBook.setPrice(info.getPrice());
		newBook.setImage(info.getImage());
		
		bookRepository.save(newBook);
		
	}

	@Override
	public List<Books> searchByName(String name) {
		String searchName = "%" + name + "%";
	    return bookRepository.findByNameContainingIgnoreCase(searchName);
	}

}
