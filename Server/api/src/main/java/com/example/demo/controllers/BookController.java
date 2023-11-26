package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.dtos.MessageDTO;
import com.example.demo.models.dtos.SaveBookDTO;
import com.example.demo.models.entities.Books;
import com.example.demo.services.BookService;

@RestController
@RequestMapping("/books")
@CrossOrigin("*")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/all")
	public ResponseEntity<?> booksAll(@RequestParam(name = "genre", required = false) String genre) {
		List<Books> books;

		if (genre != null && !genre.isEmpty()) {

			books = bookService.getByGenre(genre);
		} else {

			books = bookService.getAll();
		}

		if (books != null) {
			return ResponseEntity.ok(books);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/viewBook")
	public ResponseEntity<?> getBookById(@RequestParam(name = "id") Integer id) {
		Books book = bookService.findOneById(id);
		if (book != null) {
			return ResponseEntity.ok(book);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/update")
    public ResponseEntity<?> updateBook(@RequestParam(name = "id") Integer id, @RequestBody SaveBookDTO info) {
            Books existingBook = bookService.findOneById(id);

            if (existingBook == null) {
                return ResponseEntity.notFound().build();
            }

            try {
    			bookService.updateCreatedBooks(id, info);
    			return new ResponseEntity<>(
    					new MessageDTO("This event was updated " + info), HttpStatus.CREATED);
    		} catch (Exception e) {
    			e.printStackTrace();
    			return new ResponseEntity<>(
    					new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
   		}
    }
	
	@GetMapping("/search")
	public ResponseEntity<?> searchBooksByName(@RequestParam(name = "name") String name) {
		
		System.out.println("Search Name: " + name);
	    Books books = bookService.findOneByName(name);

	    if (books != null) {
	        return ResponseEntity.ok(books);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
}
