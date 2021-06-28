# Film Query Project

## Overview

This project simulates a film query application.  It allows users to search a database containing information regarding films, such as film title, year of release, rating, brief description, and cast members. The user is able to query the various information located within the database using an interactive menu.

## Technology Used

* Java
* Scanner
* Conditionals
* Looping
* Object-Oriented Design
* Try-Catch Statements
* Exceptions
* SQL

## How to Run

Upon starting the program, the user will be greeted with a welcome message and a menu containing three choices: 1) Look up a film by ID number, 2) Look up a film by a search keyword, and 3) exit the program.  When the user chooses the first option, they will be prompted with a message to enter the ID number of the film they wish to see information about, and the single film attached to the ID entered will be displayed.  The information provided includes: a confirmation of the film ID entered, the film title, the year the film was released, the film rating, a brief description of the film, the language of the film, and the cast members staring in the film.  In addition to the film information displayed, the same welcome menu will be displayed again allowing the user to choose one of the same three options.  When the user enters the second menu option and decides to query a film using a keyword, they will be prompted to do so.  After entering the keyword, all films containing it will be displayed with the same information shown by choosing menu option one -- the only difference with option two is multiple films might be displayed.  In both option one and option two, if their query yields no results, a message will be displayed informing the user.  Upon entering menu option three, a goodbye message will be displayed, and the application will terminate.  

## Lessons Learned

During the course of this project, I learned the importance of ensuring all queries are completed and tested prior to incorporating them into the Java code.  Not doing so caused delays further along in the coding process.  Additionally, there were quite a few elements generated in the toString portions of the code which made formatting them properly important for the user interface quality.  Furthermore, having toStrings in more than one class caused a slight hiccup that needed to be troubleshot as I displayed duplicate output while running the program.  It was an easy fix, but it is something that will remain in the forefront on future projects.  
