package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Language;

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

	public Film findFilmById(int filmId) {
		Film film = null;
		Language languageName = new Language(); 

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT film.id, film.title, film.release_year, film.rating, film.description, film.language_id, "
					+ "film.rental_duration, film.rental_rate, film.length, film.replacement_cost, film.special_features, language.name " 
					+ "FROM film JOIN language ON language.id = film.language_id " + "WHERE film.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();

//		System.out.println(stmt);
			
			if (filmResult.next()) {
				film = new Film(); // create the object

//			Here is our mapping of query columns to our object fields:
				film.setId(filmResult.getInt(1));
				film.setTitle(filmResult.getString(2));
				film.setReleaseYear(filmResult.getInt("release_year"));
				film.setRating(filmResult.getString(4));
				film.setDescription(filmResult.getString(5));
				film.setLanguageId(filmResult.getString(6));                                // should this be an int?
				film.setRentalDuration(filmResult.getInt("rental_duration"));
				film.setRentalRate(filmResult.getInt("rental_rate"));
				film.setLength(filmResult.getInt("length"));
				film.setReplacementCost(filmResult.getDouble("replacement_cost"));
				film.setSpecialFeatures(filmResult.getString(11));
				languageName.setLanguageName(filmResult.getString("language.name"));
				film.setLanguageName(languageName); 
				film.setCast(findActorsByFilmId(filmResult.getInt(1)));

			}
			filmResult.close();
			stmt.close();
			conn.close(); 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return film;
	}

	public Actor findActorById(int actorId) {
		Actor actor = null;

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT actor.id, actor.first_name, actor.last_name " + "FROM actor "
					+ "JOIN film_actor ON actor.id = film_actor.actor_id" + "JOIN film ON film_actor.film_id = film.id "
					+ "WHERE film.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);

//		System.out.println(stmt);

			ResultSet actorResult = stmt.executeQuery();
			if (actorResult.next()) {
				actor = new Actor(); // create the object

				// Here is our mapping of query columns to our object fields:
				actor.setId(actorResult.getInt(1));
				actor.setFirstName(actorResult.getString(2));
				actor.setLastName(actorResult.getString(3));

			}
			actorResult.close();
			stmt.close();
			conn.close(); 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT actor.id, film.title, actor.first_name, actor.last_name " + "FROM actor "
					+ "JOIN film_actor ON actor.id = film_actor.actor_id "
					+ "JOIN film ON film_actor.film_id = film.id " + "WHERE film.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet actorResult = stmt.executeQuery();
			
//			System.out.println(stmt);

			while (actorResult.next()) {
				Actor actor = new Actor(); // create the object

				// Here is our mapping of query columns to our object fields:
				actor.setId(actorResult.getInt("actor.id"));
				actor.setFirstName(actorResult.getString("actor.first_name"));
				actor.setLastName(actorResult.getString("actor.last_name"));
				actors.add(actor);

			}
			actorResult.close();
			stmt.close();
			conn.close(); 
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return actors;
	}
	
	@Override
	public List <Film> findFilmByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();      // do not need to fill in the wacka wacka on the right side
		Language languageName = new Language(); 
		
		// sql = "SELECT yada, yada, FROM yada WHERE yada LIKE ?" 
		// prepStmt.setString(1, "%" + searchTerm + "%");
		
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT film.id, film.title, film.release_year, film.rating, film.description, film.language_id, "
					+ "language.name FROM film JOIN language ON language.id = film.language_id WHERE film.title LIKE ? "
					+ "OR film.description LIKE ?"; 

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet filmResult = stmt.executeQuery();
			
//		System.out.println(stmt);

			while (filmResult.next()) {
				Film film = new Film(); // create the object

				// Here is our mapping of query columns to our object fields:
				film.setId(filmResult.getInt(1));
				film.setTitle(filmResult.getString(2));
				film.setReleaseYear(filmResult.getInt("release_year"));
				film.setRating(filmResult.getString(4));
				film.setDescription(filmResult.getString(5));
				film.setCast(findActorsByFilmId(filmResult.getInt("film.id")));
				languageName.setLanguageName(filmResult.getString("language.name"));
				film.setLanguageName(languageName); 
				films.add(film);
				
			}
			filmResult.close();
			stmt.close();
			conn.close(); 
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return films;
	}
		
}
	
