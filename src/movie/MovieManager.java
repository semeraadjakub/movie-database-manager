package movie;

import custom.Rating;
import worker.Actor;
import worker.Animator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieManager {
    ArrayList<Movie> movies;
    public MovieManager(){
        movies = new ArrayList<Movie>();
    }

    public void addMovie(MovieType type, String title, String director, int releaseYear){
        movies.add(new LiveActionMovie(type, title, director, releaseYear));
    }

    public void addMovie(MovieType type, String title, String director, int releaseYear, List<String> actorNames){
        movies.add(new LiveActionMovie(type, title, director, releaseYear, actorNames));
    }

    public void addMovie(MovieType type, String title, String director, int releaseYear, int recommendedAge){
        movies.add(new AnimatedMovie(type, title, director, releaseYear, recommendedAge));
    }

    public void addMovie(MovieType type, String title, String director, int releaseYear, int recommendedAge, List<String> animatorNames){
        movies.add(new AnimatedMovie(type, title, director, releaseYear, recommendedAge, animatorNames));
    }

    public void deleteMovieByTitle(String title){
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getTitle().equals(title)) {
                movies.remove(i);
                i--;
            }
        }
    }

    public Movie getMovieByTitle(String title){
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getTitle().equals(title)) {
                return movies.get(i);
            }
        }
        return null;
    }

    public void editMovie(String title){
        Movie movie = getMovieByTitle(title);
        System.out.println("");
    }

    public int addRating(){
        return 0;
    }

    public ArrayList<Movie> getMovies(){
        return movies;
    }
    public void printMovieTitles(){
        System.out.println("\nSeznam filmů v databázi:");
        for(Movie movie : movies){
            System.out.println(movie.getTitle());
        }
    }

    public void printMovie(String title, boolean printRatings){
        Movie movie = getMovieByTitle(title);
        printMovie(movie, printRatings);
    }

    public void printMovie(Movie movie, boolean printRatings) {
        System.out.println("Název: " + movie.getTitle());
        System.out.println("Druh filmu: " + (movie.getType() == MovieType.ANIMATED ? "Animovaný" : "Hraný"));
        System.out.println("Režisér: " + movie.getDirector());
        System.out.println("Rok vydání: " + movie.getReleaseYear());
        if (movie.getType() == MovieType.ANIMATED) {
            System.out.println("Doporučený věk diváka: " + ((AnimatedMovie) (movie)).getRecommendedAge());
            printWorkersList(movie, false);
        } else {
            printWorkersList(movie, false);
        }

        if(printRatings){
            System.out.println();
            ArrayList<Rating> ratings = movie.getRatings();
            Collections.sort(ratings, (r1, r2) -> Integer.compare(r2.rating, r1.rating));
            for(Rating r : ratings) {
                if(r.textRating != null)
                    System.out.println("Hodnocení:\n    Počet hvězdiček: " + r.rating + (!r.textRating.equalsIgnoreCase("") ? "\n     Slovní hodnocení: " + r.textRating : ""));
                else
                    System.out.println("Hodnocení:\n    Počet hvězdiček: " + r.rating);
            }
        }

        System.out.println();
    }

    public void printWorkersList(Movie movie, boolean numbers){
        int count = 1;
        if(movie.getType() == MovieType.ANIMATED){
            ArrayList<Animator> animators = ((AnimatedMovie)(movie)).getAnimatorList();
            if(animators != null){
                System.out.println("Animátoři: ");
                for(Animator animator : animators){
                    System.out.println("    " + (numbers == true ? "[" + count + "] " : "") + animator.getName());
                    count++;
                }
            } else {
                System.out.println("Animátoři neuvedeni.");
            }
        } else {
            ArrayList<Actor> actors = ((LiveActionMovie)(movie)).getActorList();
            if(actors != null){
                System.out.println("Herci: ");
                for(Actor actor : actors){
                    System.out.println("    " + (numbers == true ? "[" + count + "] " : "") + actor.getName());
                    count++;
                }
            } else {
                System.out.println("Herci neuvedeni.");
            }
        }
    }

    public void printAllMovies(){
        for(Movie movie : movies){
            printMovie(movie, false);
        }
    }

    public boolean rateMovie(Movie movie, int rating, String textRating){
        if(movie != null){
            return movie.rate(rating, textRating);
        }
        return false;
    }

    public boolean rateMovie(Movie movie, int rating){
        if(movie != null){
            return movie.rate(rating);
        }
        return false;
    }

    public boolean rateMovie(String title, int rating, String textRating){
        return rateMovie(getMovieByTitle(title), rating, textRating);
    }

    public boolean rateMovie(String title, int rating){
        return rateMovie(getMovieByTitle(title), rating);
    }

    public String getMoviesByAnimator(){
        return null;
    }

    public String getMoviesByActor(){
        return null;
    }

    public boolean saveMovieToFile(String name){
        return false;
    }

    public boolean loadMovieFromFile(String name){
        return false;
    }
}
