package com.bookManagement.service;

import java.util.List;
import java.util.Scanner;
import com.bookManagement.dao.BookDAO;
import com.bookManagement.to.Book;

public class BookFeatureService {
	Scanner sc = new Scanner(System.in);
	BookDAO bookDAO = new BookDAO();

	public void addBook() {
		System.out.println("Please Enter book details");
		System.out.println("Enter Book Name:");
		String bookName = sc.next();
		System.out.println("Enter Author's Name:");
		String authorName = sc.next();
		System.out.println("Enter Book Description:");
		String bookDescription = sc.next();
		System.out.println("Enter Book ID:");
		String bookID = sc.next();

		Book book = new Book(bookName, authorName, bookDescription, bookID);
		bookDAO.addBookDB(book);
	}

	public void deleteBook() {
		System.out.println("----- Enter Book ID you want to delete !!! -----");
		String bookDelete = sc.next();

		bookDAO.deleteBook(bookDelete);
	}

	public void searchBook() {
		System.out.println("----- Enter Book ID you want to search !!! -----\n");
		String bookSearch = sc.next();

		Book bookDetail = bookDAO.getBook(bookSearch);

		System.out.println("\t---Book Details---\n");
		System.out.println("Book Name:" + bookDetail.getBookName());
		System.out.println("Author Name:" + bookDetail.getAuthorName());
		System.out.println("Book Description:" + bookDetail.getBookDescription());
		System.out.println("Book ID:" + bookDetail.getBookID() + "\n" + "\n");
	}

	public void searchAllBooks() {
		System.out.println("----- All Books are peresent here !!! -----\n");
		List<Book> allBooks = bookDAO.getAllBooks();

		for (Book book : allBooks) {
			System.out.print("Book Name:" + book.getBookName() + " || ");
			System.out.print("Author Name:" + book.getAuthorName() + " || ");
			System.out.print("Book Description:" + book.getBookDescription() + " || ");
			System.out.println("Book ID:" + book.getBookID() + "\n\n");
		}
	}
}
