package com.bookapp.bean;

public class Book {
	
	private String title;
	private String author;
	private String category;
	private int bookId;
	private int price;
	
	public Book(String title, int bookId, String author,int price ,String category) {
		this.title = title;
		this.author = author;
		this.category = category;
		this.bookId = bookId;
		this.price = price;
	}

	public Book() {
		super();
	
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", category=" + category + ", bookId=" + bookId
				+ ", price=" + price + "]";
	}
	

}
