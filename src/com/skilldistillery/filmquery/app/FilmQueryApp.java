package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) throws SQLException {
    FilmQueryApp app = new FilmQueryApp();
//   app.test();
   app.launch();
   displayMenu(); 
  }

  private void test() throws SQLException {
    Film film = db.findFilmById(1);
    System.out.println(film);
  }

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
    
  }
  
  private void run() {

		boolean menu = true;
		// menu needs displayed upon program start

		while (menu) {

			displayMenu();

			int menuChoice = scanner.nextInt();
			scanner.nextLine();

			switch (menuChoice) {

			case 1:
				findFilmById();
				break;
			case 2:
				findFilmByKeyword(); 
				break;
			case 3:
				quitApp();
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

	}
  
	protected void quitApp() {
		System.out.println("Thank you for using this app!  Your participation gets two thumbs up!");
		scanner.close();
		System.exit(0);

	}

}
