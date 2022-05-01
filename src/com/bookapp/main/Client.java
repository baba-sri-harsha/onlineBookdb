package com.bookapp.main;

import java.util.List;
import java.util.Scanner;

import com.bookapp.bean.Book;
import com.bookapp.dao.BookImpl;
import com.bookapp.dao.BookInter;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class Client {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		BookInter bookInter = new BookImpl();

		while (true) {
			System.out.println("Enter A for Admin");
			System.out.println("Enter C for Customer");
			System.out.println("Enter E for Exit");

			String input = s.next();
			char inputType = input.toUpperCase().charAt(0);

			if (inputType == 'A') {
				System.out.println("Enter 1 to Add Book");
				System.out.println("Enter 2 to Delete Book");
				System.out.println("Enter 3 to Get Book data by Book ID");
				System.out.println("Enter 4 to Update Book");

				switch (s.nextInt()) {
				case 1:
					System.out.println("Enter Book Title :");
					String title = s.next();

					System.out.println("Enter Book Author :");
					String author = s.next();

					System.out.println("Enter Book Category :");
					String category = s.next();

					System.out.println("Enter Book ID");
					int bookId = s.nextInt();

					System.out.println("Enter Book Price");
					int price = s.nextInt();

					bookInter.addBook(new Book(title, bookId, author, price, category));
					break;
				case 2:
					System.out.println("Enter Book ID ");
					bookId = s.nextInt();
					try {
						boolean result = bookInter.deleteBook(bookId);
						if (result)
							System.out.println("Sucessfully deleted");

					} catch (BookNotFoundException e) {
						System.out.println(e.getMessage());
					}
					break;

				case 3:
					System.out.println("Enter Book ID");
					bookId = s.nextInt();
					try {
						System.out.println(bookInter.getBookbyId(bookId));
					} catch (BookNotFoundException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 4:
					System.out.println("Enter the  bookid");
					bookId = s.nextInt();
					System.out.println("Enter the  price value ");
					price = s.nextInt();

					boolean check = bookInter.updateBook(bookId, price);
					if (check)
						System.out.println("Successfully Updated ..!");
					else
						System.out.println("Updation Failed");

					break;

				default:
					System.out.println("Invalid Input");
					break;
				}

			} else if (inputType == 'C') {
				System.out.println("Enter 1 to Get All Books Details");
				System.out.println("Enter 2 to Get All Books by Author");
				System.out.println("Enter 3 to Get All Books by Category");

				switch (s.nextInt()) {
				case 1:
					List<Book> bookList = bookInter.getAllBooks();
					for (Book book : bookList) {
						System.out.println(book);
					}

					break;

				case 2:
					System.out.println("Enter Author name :");
					String author = s.next();
					try {
						for (Book book : bookInter.getBookbyAuthor(author)) {
							System.out.println(book);
						}
					} catch (AuthorNotFoundException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 3:
					System.out.println("Enter Category name :");
					String category = s.next();
					try {
						for (Book book : bookInter.getBookbyCategory(category)) {
							System.out.println(book);
						}
					} catch (CategoryNotFoundException e) {
						System.out.println(e.getMessage());
					}
					break;

				default:
					System.out.println("Invalid Input");
					break;
				}
			} else if (inputType == 'E') {
				System.out.println("program has been ended");
				System.exit(0);
			} else {
				System.out.println("Enter the correct input");
				System.out.println();
				
			}

		}
	}

}
