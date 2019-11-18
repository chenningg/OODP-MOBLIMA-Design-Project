Welcome to our MOBLIMA app.

The class where you can start the MOBLIMA app is called DriverApp under src/boundaries package. It has a main class that when run, 
starts up the app and brings you to the first menu. Reviews can be accessed after you have selected a movie. You have to make a booking
before you can check booking history. Showtimes on both Staff and Customer side can be accessed after accessing the movie you want to
view/edit/add/remove the showtime for.

Our StaffAccount for your use has Username: User1 (case-sensitive) and the Password: 123

In any menu selection, enter 0 in order to return to the previous menu.

The preexisting data is already initialised, but if there are any loading issues, please go to the DataInitialiser under the src/utils 
package and run that class (using Run Configurations if you have to). It will throw out filenotfound errors and exceptions but we have dealt with them and they do not cause errors
within the program. If you want to reset the data, please do run DataInitialiser.

We have initialised a company, the 3 cineplexes, 9 cinemas, 9 movies and a number of showtimes and reviews such that our app has full
capabilities when started up.
