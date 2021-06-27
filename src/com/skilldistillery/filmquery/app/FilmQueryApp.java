package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();
	Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
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

		while (menu) {

			displayMenu();

			int userChoice = input.nextInt();
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
				System.out.println(
						" That was not a valid selection. Please choose a number between 1 and 3, as listed in the menu.");
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
		
		System.out.println("Please enter a film ID number: ");
		int filmId = input.nextInt();
		
		try {
			System.out.println(db.findFilmById(filmId));
		} catch (SQLException e) {
		
			e.printStackTrace();
		} 
		
		// if the film is not found, a message appears saying so.
		// if the film is found, display title, year, rating, and description.
		
		// (STRETCH GOAL) display a sub menu to decide whether to return to the main menu or view all film details
	}
	
	private void filmSearchByKeyword() {
		
		System.out.println("Please enter a search keyword: ");
		String keyword = input.nextLine();
		
		db.findFilmByKeyword(keyword); 
		
		System.out.println(db.findFilmByKeyword(keyword));
	

		// if the film is not found, a message appears saying so.
		// if the film is found, display title, year, rating, and description.
		// the keyword should search the title and description
		
	
		
	}
	

}
