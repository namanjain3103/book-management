package com.bookManagement.service;

import java.util.Scanner;
import com.bookManagement.dao.*;
import com.bookManagement.to.*;

public class UserAccessService {

	Scanner sc = new Scanner(System.in);
	UserDAO userDAO = new UserDAO();
	UserFeatureService userFeatureService = new UserFeatureService();
	BookFeatureService bookFeatureService = new BookFeatureService();

	public void signUp() {
		System.out.println("\n----- Register as new User!!! -----\n");
		System.out.println("Enter First Name:");
		String firstName = sc.next();
		System.out.println("Enter Last Name:");
		String lastName = sc.next();
		System.out.println("Enter Email ID:");
		String email = sc.next();
		String userType = userTypeValidate();
		System.out.println("Enter Username:");
		String username = sc.next();
		String password = userPasswordValidate();

		User user = new User(firstName, lastName, email, userType, username, password);
		userDAO.addUser(user);
	}

	public String userTypeValidate() {
		System.out.println("Enter User Type (admin / user):");
		String typeOfUser = sc.next();
		if ("admin".equals(typeOfUser) || "user".equals(typeOfUser))
			;
		else {
			System.out.println("--- Not a valide UserType. Try again ---\n");
			userTypeValidate();
		}
		return typeOfUser;
	}

	public String userPasswordValidate() {
		System.out.println("Enter Pasword:");
		String password = sc.next();
		if (password.length() < 8){
			System.out.println("--- password should of atleast 8 digits. Try again ---\n");
			userPasswordValidate();
		}
		return password;
	}

	public void signIn() {
		System.out.println("-----Enter your details to log in!!!-----\n");
		System.out.println("Enter Username:");
		String username = sc.next();
		System.out.println("Enter Password:");
		String password = sc.next();
		User user = new User(username, password);
		User isUser = userDAO.getUser(user);
		if (isUser != null) {

			System.out.println("\n----- Login successful !!! -----\n");

			String role = isUser.getUserType();

			if ("admin".equals(role)) {
				adminUser(isUser);
			} else {
				normalUser(isUser);
			}

		} else {

			System.out.println("----- Login failed!!! Invalid credentials. ------\n\n");
		}
	}

	public void normalUser(User isUser) {

		int userMenu = 0;
		do {
			System.out.println("\t__Welcome to User Panel !!!__\n\n" + "Please Enter Your choice:");
			System.out.println(
					" 1.View Profile\n" + " 2.Search Book \n" + " 3.Show All Book \n" + " 0. Log Out");

			userMenu = sc.nextInt();

			switch (userMenu) {
			case 1:
				userFeatureService.getProfile(isUser);
				break;
			case 2:
				bookFeatureService.searchBook();
				break;
			case 3:
				bookFeatureService.searchAllBooks();
				break;
			case 0:
				System.out.println("\n" + "----- Log Out Successfully!!! -----\n\n");
				break;
			default:
				System.out.println("----- Entered wrong number Please try again !!! -----\n\n");
				break;
			}
		} while (userMenu != 0);
	}

	public void adminUser(User isUser) {

		int userMenu = 0;
		do {
			System.out.println("\t__Welcome to Admin User Panel !!!__\n\n" + "Please Enter Your choice:");
			System.out.println(" 1.View Profile \n" + " 2.Add Book  \n" + " 3.Delete Book  \n"
					+ " 4.Search Book \n" + " 5.Delete User \n" + " 6.Show All book \n"
					+ " 7.Show All Users  \n" + " 0. Log Out");

			userMenu = sc.nextInt();

			switch (userMenu) {
			case 1:
				userFeatureService.getProfile(isUser);
				break;
			case 2:
				bookFeatureService.addBook();
				break;
			case 3:
				bookFeatureService.deleteBook();
				break;
			case 4:
				bookFeatureService.searchBook();
				break;
			case 5:
				userFeatureService.deleteUser();
				break;
			case 6:
				bookFeatureService.searchAllBooks();
				break;
			case 7:
				userFeatureService.allUser();
				break;
			case 0:
				System.out.println("----- Log Out Successfully!!! -----\n\n");
				break;
			default:
				System.out.println("----- Entered wrong number Please try again !!! -----\n\n");
				break;
			}
		} while (userMenu != 0);

	}

}
