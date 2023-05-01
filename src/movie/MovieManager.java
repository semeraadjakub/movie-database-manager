package movie;

import java.util.ArrayList;
import java.util.List;

public class MovieManager {
    ArrayList<Movie> movies;
    public MovieManager(){
        movies = new ArrayList<Movie>();
    }

    public int addMovie(MovieType type, String title, String director, int releaseYear){
        movies.add(new LiveActionMovie(type, title, director, releaseYear));
        return 0;
    }

    public int addMovie(MovieType type, String title, String director, int releaseYear, List<String> actorNames){
        movies.add(new LiveActionMovie(type, title, director, releaseYear, actorNames));
        return 0;
    }

    public int addMovie(MovieType type, String title, String director, int releaseYear, int recommendedAge){
        movies.add(new AnimatedMovie(type, title, director, releaseYear, recommendedAge));
        return 0;
    }

    public int addMovie(MovieType type, String title, String director, int releaseYear, int recommendedAge, List<String> animatorNames){
        movies.add(new AnimatedMovie(type, title, director, releaseYear, recommendedAge, animatorNames));
        return 0;
    }

    public int deleteMovieByTitle(String title){
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getTitle().equals(title)) {
                movies.remove(i);
                i--;
            }
        }
        return 0;
    }

    public int editMovie(){
        return 0;
    }

    public int addRating(){
        return 0;
    }

    public ArrayList<Movie> getMovies(){
        return movies;
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
