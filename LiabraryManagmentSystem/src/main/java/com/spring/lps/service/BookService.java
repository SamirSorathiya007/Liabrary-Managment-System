package com.spring.lps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.lps.entity.Book;
import com.spring.lps.entity.User;
import com.spring.lps.repository.BookRepository;
import com.spring.lps.repository.UserRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Book addBook(Book book) {
		return bookRepository.save(book);
	}
	
	public Book findById(Long id) {
		return bookRepository.findById(id).orElse(null);
	}
	
	public List<Book> findAll(){
		return bookRepository.findAll();
	}
	
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}
	
	public Book borrowBook(Long bookId, Long userId) {
		Book book = findById(bookId);
		User user = userRepository.findById(userId).orElse(null);
		
		if(book != null && !book.isBorrowed() && user != null) {
			book.setBorrowedBy(user);
			book.setBorrowed(true);
			return addBook(book);
		}
		
		return null;
	}
	
	public Book returnBook(Long bookId) {
		Book book = findById(bookId);
		if(book != null && book.isBorrowed()) {
			book.setBorrowedBy(null);
			book.setBorrowed(false);
			return addBook(book);
		}
		return null;
	}
}
