package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();
	Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
		Film film = new Film(); 
//      app.test();
		app.launch();
		
	}

	private void test() throws SQLException {
		Film film = db.findFilmById(1);
		System.out.println(film);
	}

	private void launch() {
		
		startUserInterface();

		input.close();
	}

	private void startUserInterface() {
		
		boolean menu = true;
		// menu needs displayed upon program start and exit only when user chooses to do so
		int userChoice = 0;
	
		while (menu) {
				
				displayMenu(); 
				
				try {
					userChoice = input.nextInt();
					
				} catch (InputMismatchException e) {
					System.out.println("You must enter a number.");
					//	e.printStackTrace();
				} 
				input.nextLine();

			switch (userChoice) {

			case 1:
				filmSearchById();
				break;
			case 2:
				filmSearchByKeyword();
				break;
			case 3:
				System.out.println("Thanks for being a part of this! See ya!");
				menu = (false);
				break;
			default:
				System.out.println(" That was not a valid selection. Please choose a number between 1 and 3, as listed in the menu.");
				break;
			}

		}
		
	}

	private static void displayMenu() {

		System.out.println("                                                                  ");
		System.out.println("                       What would you like to do?                 ");
		System.out.println("                                                                  ");
		System.out.println("              ********************************************        ");
		System.out.println("                                                                  ");
		System.out.println("                           (choose a number)                      ");
		System.out.println("                                                                  ");
		System.out.println("                 1. Look up a film by its ID                      ");
		System.out.println("                 2. Look up a film by a search keyword            ");
		System.out.println("                 3. Exit                                          ");
		System.out.println("                                                                  ");
		System.out.println("              ********************************************        ");
		System.out.println("                                                                  ");

	}
	
	private void filmSearchById() {
		
		int filmId = 0;
		
		
		
		while (filmId == 0) {
			try {
	
				System.out.println("Please enter a film ID number: ");
				filmId = input.nextInt();
			} catch (InputMismatchException e1) {
				System.out.println("That was not a valid entry. Please enter a number value.");
				//			e1.printStackTrace();
			} 
			input.nextLine(); 
		}
		try {
			System.out.println(db.findFilmById(filmId));
		} catch (SQLException e) {
		
			e.printStackTrace();
		} 
		
		// if the film is not found, a message appears saying so.
		// if the film is found, display title, year, rating, and description.		
		// (STRETCH GOAL) display a sub menu to decide whether to return to the main menu or view all film details.
	}
	
	private void filmSearchByKeyword() {
		
		System.out.println("Please enter a search keyword: ");
		String keyword = input.nextLine();
		
		List<Film> filmList = db.findFilmByKeyword(keyword); 
		
		if(filmList.isEmpty()) {
			System.out.println("Your keyword did not yield any results.");
		} else {
			for (Film film : filmList) {
				System.out.println(film);
			}
		}

		// if the film is not found, a message appears saying so.
		// if the film is found, display title, year, rating, and description.
		// the keyword should search the title and description.
		
	}
	
}
