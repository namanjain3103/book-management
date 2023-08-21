package com.bookManagement.service;

import java.util.List;
import java.util.Scanner;
import com.bookManagement.dao.UserDAO;
import com.bookManagement.to.User;


public class UserFeatureService {
	Scanner sc = new Scanner(System.in);
	UserDAO userDAO = new UserDAO();

	public void getProfile(User isUser) {
		System.out.println("----- View Profile -----\n");
		System.out.println("First Name:" + isUser.getFirstName());
		System.out.println("Last Name:" + isUser.getLastName());
		System.out.println("Email:" + isUser.getEmail());
		System.out.println("Username:" + isUser.getUsername() + "\n\n");
	}

	public void deleteUser() {
		System.out.println("----- Enter username of the user you want to delete !!! -----");
		String userDelete = sc.next();
		userDAO.deleteUser(userDelete);
	}

	public void allUser() {
		System.out.println("----- Details of every User are peresent here !!! -----\n");
		List<User> allUserList = userDAO.getAllUsers();
		for(User userDetails: allUserList) {
		System.out.print("First Name:" + userDetails.getFirstName() + " || ");
		System.out.print("Last Name:" + userDetails.getLastName() + " || ");
		System.out.print("Email ID:" + userDetails.getEmail() + " || ");
		System.out.print("User Role:" + userDetails.getUserType() + " || ");
		System.out.print("Username:" + userDetails.getUsername() + " || ");
		System.out.println("Password:" + userDetails.getPassword()+"\n\n");
		}
	}
}
