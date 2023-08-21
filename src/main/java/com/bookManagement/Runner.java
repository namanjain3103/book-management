package com.bookManagement;

import java.util.Scanner;
import com.bookManagement.service.UserAccessService;

public class Runner {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int mainMenuChoice = 0;
		do {
			System.out.println("\t---------- Welcome to Bookshalla: The Bookstore!!! ----------\n\n" + "Please Enter Your choice:");
			System.out.println(" 1. Sign Up \n" + " 2. Sign In \n" + " 0. Exit");

			mainMenuChoice = sc.nextInt();

			UserAccessService userAccessService = new UserAccessService();

			switch (mainMenuChoice) {
			case 1:
				userAccessService.signUp();
				break;
			case 2:
				userAccessService.signIn();
				break;
			case 0:
				System.out.println("\t---------- Exit Successfully!!! ----------\n\n");
				break;
			default:
				System.out.println("----- Entered wrong number Please try again !!! -----\n\n");
				break;
			}
		} while (mainMenuChoice != 0);

		sc.close();
	}
}
