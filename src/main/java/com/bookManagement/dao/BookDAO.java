package com.bookManagement.dao;

import com.bookManagement.to.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookManagement.utlis.DBUtils;

public class BookDAO {

	Connection dbConnection = DBUtils.getConnection();
	PreparedStatement statement;

	public void addBookDB(Book book) {
		try {
			statement = dbConnection.prepareStatement("insert into book_details values(?,?,?,?)");
			statement.setString(1, book.getBookName());
			statement.setString(2, book.getAuthorName());
			statement.setString(3, book.getBookDescription());
			statement.setString(4, book.getBookID());
			statement.executeUpdate();
			System.out.println( "----- New Book Added!!! -----" + "\n\n");
		} catch (SQLException e) {
			System.out.println(" Problem in com.bookManagement.dao.BookDAO.addUser()");
			e.printStackTrace();
		}
	}

	public void deleteBook(String bookDelete) {
		try {
			statement = dbConnection.prepareStatement("delete from book_details where book_id = ?");
			statement.setString(1, bookDelete);
			int resultSet = statement.executeUpdate();
			if(resultSet != 0) {
				System.out.println("----- Book deleted successfully!!! -----\n\n");
			} else {
				System.out.println("----- Book not found or could not be deleted!!! -----\n\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Book getBook(String bookSearch) {
		Book bookDetail = null;
		try {
			statement = dbConnection.prepareStatement("select * from book_details where book_id = ?");
			statement.setString(1, bookSearch);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {

				String bookName = resultSet.getString(1);
				String authorName = resultSet.getString(2);
				String bookDesc = resultSet.getString(3);
				String bookID = resultSet.getString(4);

				bookDetail = new Book(bookName, authorName, bookDesc, bookID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookDetail;
	}

	public List<Book> getAllBooks() {
		Book bookDetails = null;
		List<Book> getBookList = new ArrayList<>();
		try {
			statement = dbConnection.prepareStatement("select * from book_details ");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String bookName = resultSet.getString(1);
				String authorName = resultSet.getString(2);
				String bookDesc = resultSet.getString(3);
				String bookID = resultSet.getString(4);

				bookDetails = new Book(bookName, authorName, bookDesc, bookID);
				getBookList.add(bookDetails);	
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getBookList;
	}

}