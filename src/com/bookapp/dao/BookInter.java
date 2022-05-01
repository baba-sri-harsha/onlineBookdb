package com.bookapp.dao;

import java.util.*;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public interface BookInter {
	
	//called by admin
	void addBook(Book book);
	boolean deleteBook(int bookId) throws BookNotFoundException;
	Book getBookbyId(int bookId) throws BookNotFoundException;
	boolean updateBook(int bookId, int price);
	
	// called by customer
	List<Book> getAllBooks();
	List<Book> getBookbyAuthor(String author) throws AuthorNotFoundException;
	List<Book> getBookbyCategory(String category) throws CategoryNotFoundException;
	
	
}
