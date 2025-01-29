package com.spring.lps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.lps.entity.Book;
import com.spring.lps.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@PostMapping("/save")
	public ResponseEntity<Book> addBook(@RequestBody Book book){
		return new ResponseEntity<Book>(bookService.addBook(book), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> findByBookId(@PathVariable("id") Long id){
		Book book = bookService.findById(id);
		
		if(book != null)
			return new ResponseEntity<Book>(book, HttpStatus.OK);
		else
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping
	public ResponseEntity<List<Book>> findAllBook(){
		return new ResponseEntity<List<Book>>(bookService.findAll(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public void deleteBookById(@PathVariable("id") Long id){
		bookService.deleteBook(id);
	}
	
	@PostMapping("/borrow/{userId}/{bookId}")
	public ResponseEntity<Book> borrowBook(@PathVariable("userId") Long userId, @PathVariable("bookId") Long bookId){
		Book book = bookService.borrowBook(bookId, userId);
		
		if(book != null)
			return ResponseEntity.ok(book);
		else
			return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/return/{bookId}")
	public ResponseEntity<Book> returnBook(@PathVariable("bookId") Long bookId){
		Book book = bookService.returnBook(bookId);
		
		if(book != null)
			return ResponseEntity.ok(book);
		else
			return ResponseEntity.badRequest().build();
	}
}
