package com.skilldistillery.filmquery.database;

import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private String user = "student"; 
	private String pass = "student"; 
	
	static { 
		try { 
			Class.forName("com.mysql.jdbc.Driver"); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
  @Override
  public Film findFilmById(int filmId) {
    return null;
  }

}
