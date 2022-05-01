package com.bookapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class BookImpl implements BookInter{
	
	String addBookQuery="insert into book values (?,?,?,?,?)";
	String deleteBookQuery="delete from book where bookId=?";
	String getBookbyIdQuery="select * from book where bookId=?";
	String updateBookQuery="update book set price = ? where bookId = ?";
	String getAllBooksQuery = "select * from book";
	String getBookbyAuthorQuery="select * from book where author=?";
	String getBookbyCategoryQuery="select * from book where category=?";
	
	Connection connection = ModelDAO.openConnection();
	
	PreparedStatement preparedStatement=null;
	Statement statement=null;
	ResultSet resultSet= null;
	
	@Override
	public void addBook(Book book) {
		try {
			preparedStatement= connection.prepareStatement(addBookQuery);

			
			preparedStatement.setString(1, book.getTitle());
			preparedStatement.setInt(2, book.getBookId());
			preparedStatement.setString(3, book.getAuthor());
			preparedStatement.setInt(4, book.getPrice());
			preparedStatement.setString(5, book.getCategory());
			
			
			boolean result=preparedStatement.execute();
			if(!result)
				System.out.println("sucessfully inserted");
			else
				System.out.println("unsucessfull insertion");
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(preparedStatement!=null) {
				try {
					preparedStatement.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			} //ModelDAO.closeConnection();
		}	
		
	}
	
	@Override
	public boolean deleteBook(int bookid) throws BookNotFoundException {
		int result=0;
		try {
			preparedStatement= connection.prepareStatement(deleteBookQuery);
			preparedStatement.setInt(1, bookid);
			result=preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(preparedStatement!=null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		  }
			//ModelDAO.closeConnection();
		}
		if(result!=0) {
			return true;
		}else {
			throw new BookNotFoundException("no book with the given "+bookid + "found");
		}
	}

	@Override
	public Book getBookbyId(int bookid) throws BookNotFoundException {
		Book book=null;
		try {
			preparedStatement = connection.prepareStatement(getBookbyIdQuery);
			preparedStatement.setInt(1, bookid);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next() ) {
				book = new Book(resultSet.getString("title")
						, resultSet.getInt("bookId")
						, resultSet.getString("author"),
						resultSet.getInt("price"), resultSet.getString("category"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			//ModelDAO.closeConnection();
		}
		if (book==null) {
			throw new BookNotFoundException("book not found");
		}
		return book;
	}

	

	@Override
	public boolean updateBook(int bookid, int price) {
		int result = 0;
		try {
			 preparedStatement = connection.prepareStatement(updateBookQuery);
			preparedStatement.setInt(1, price);
			preparedStatement.setInt(2, bookid);
			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//ModelDAO.closeConnection();
		}
		if (result != 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Book> getAllBooks() {
		Book book=null;
		List<Book> bookList = new ArrayList<Book>();
		try {
			preparedStatement= connection.prepareStatement(getAllBooksQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				book = new Book(
				resultSet.getString(1),
				resultSet.getInt(2),
				resultSet.getString(3),
				resultSet.getInt(4),
				resultSet.getString(5));
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			//ModelDAO.closeConnection();
		}
		return bookList;
	}

	@Override
	public List<Book> getBookbyAuthor(String author) throws AuthorNotFoundException{
		List<Book> bookList = new ArrayList<Book>();
		Book book=null;
		try {
			preparedStatement = connection.prepareStatement(getBookbyAuthorQuery);
			preparedStatement.setString(1, author);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				 book = new Book(
				resultSet.getString(1),
				resultSet.getInt(2),
				resultSet.getString(3),
				resultSet.getInt(4),
				resultSet.getString(5));
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			//ModelDAO.closeConnection();
		}
		if (bookList.isEmpty()) {
			throw new AuthorNotFoundException("no books found with author " + author);
		}
		return bookList;


	}

	
	@Override
	public List<Book> getBookbyCategory(String category) throws CategoryNotFoundException {
		List<Book> bookList = new ArrayList<Book>();
		Book book=null;
		try {
			preparedStatement = connection.prepareStatement(getBookbyCategoryQuery);
			preparedStatement.setString(1, category);
			ResultSet resultSet = preparedStatement.executeQuery();
		    while (resultSet.next()) {
				 book = new Book(
				resultSet.getString(1),
				resultSet.getInt(2),
				resultSet.getString(3),
				resultSet.getInt(4),
				resultSet.getString(5));
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			//ModelDAO.closeConnection();
		}
		if (bookList.isEmpty()) {
			throw new CategoryNotFoundException("no books found with category " + category);
		}
		return bookList;

	}	}

	

	


