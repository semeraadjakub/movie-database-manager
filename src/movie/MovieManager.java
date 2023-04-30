package movie;

import java.util.ArrayList;

public class MovieManager {
    ArrayList<Movie> movies;
    public MovieManager(){
        movies = new ArrayList<Movie>();
    }

    public int addMovie(MovieType type, String movieName, String directorName){
        return 0;
    }

    public int deleteMovie(){
        return 0;
    }

    public int editMovie(){
        return 0;
    }

    public int addRating(){
        return 0;
    }

    public String getMovies(){
        return null;
    }

    public String getMoviesByAnimator(){
        return null;
    }

    public String getMoviesByActor(){
        return null;
    }

    public String getMovieByName(String name){
        return null;
    }

    public boolean saveMovieToFile(String name){
        return false;
    }

    public boolean loadMovieFromFile(String name){
        return false;
    }
}
