package movie;

import Serialization.*;
import custom.Rating;
import worker.Actor;
import worker.Animator;

import java.io.File;
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

    public void printMoviesTitles(boolean numbers){
        for(int i = 0; i < movies.size(); i++){
            System.out.println("[" + (i+1) + "] " + movies.get(i).getTitle());
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

    public boolean saveMovieToFile(int index){
        Movie movie = movies.get(index);
        if(movie != null){
            String title = movies.get(index).getTitle();
            int type = movie.getType().ordinal();
            title = title.replaceAll(" ", "-");
            MDatabase moviedb = new MDatabase("db");
            MObject movieObj = new MObject("MovieObject");
            movieObj.add(MField.Integer("type", type));
            movieObj.add(new MString("title", movie.title));
            movieObj.add(new MString("director", movie.director));
            movieObj.add(MField.Integer("releaseYear", movie.releaseYear));

            int[] starRatings = new int[movie.ratings.size()];
            String[] textRatings = new String[movie.ratings.size()];
            for(int i = 0; i < movie.ratings.size(); i++){
                starRatings[i] = movie.ratings.get(i).rating;
                textRatings[i] = movie.ratings.get(i).textRating;
            }
            movieObj.add(MField.Integer("ratingCount", starRatings.length));
            movieObj.add(MArray.Integer("rating", starRatings));
            for(int i = 0; i < textRatings.length; i++){
                movieObj.add(new MString(("textRating" + Integer.toString(i+1)), textRatings[i]));
            }

            if(movie.getType() == MovieType.ANIMATED){
                movieObj.add(MField.Integer("recommendedAge", ((AnimatedMovie)(movie)).getRecommendedAge()));
                movieObj.add(MField.Integer("animatorCount", ((AnimatedMovie)(movie)).getAnimatorList().size()));
                for(int i = 0; i < ((AnimatedMovie)(movie)).getAnimatorList().size(); i++){
                    movieObj.add(new MString(("animator" + Integer.toString(i+1)), ((AnimatedMovie)(movie)).getAnimatorList().get(i).getName()));
                }
            } else {
                movieObj.add(MField.Integer("actorCount", ((LiveActionMovie)(movie)).getActorList().size()));
                for(int i = 0; i < ((LiveActionMovie)(movie)).getActorList().size(); i++){
                    movieObj.add(new MString(("actor" + Integer.toString(i+1)), ((LiveActionMovie)(movie)).getActorList().get(i).getName()));
                }
            }
            moviedb.add(movieObj);
            moviedb.serializeToFile(title + ".movie");
            System.out.println("Film byl uložen do souboru pod názvem \"" + title + ".movie\"");
            return true;
        }
        return false;
    }

    public boolean loadMovieFromFile(String path){
        path = path.replaceAll(" ", "-");
        MDatabase movieDeserialized = MDatabase.Deserialize(path);
        MObject movie = movieDeserialized.findObject("MovieObject");
        int type = movie.findField("type").getInt();
        String title = movie.findString("title").getString();
        String director = movie.findString("director").getString();
        int releaseYear = movie.findField("releaseYear").getInt();
        int ratingCount = movie.findField("ratingCount").getInt();
        int[] ratings = movie.findArray("rating").getIntArray();
        String[] textRatings = new String[ratings.length];
        for(int i = 0; i < textRatings.length; i++){
            MString obj = movie.findString(("rating" + Integer.toString(i+1)));
            String txtRating;
            if(obj == null)
                txtRating = new String("");
            else
                txtRating = obj.getString();
            textRatings[i] = txtRating;
        }

        if(type == 0){
            int recommendedAge = movie.findField("recommendedAge").getInt();
            int animatorCount = movie.findField("animatorCount").getInt();
            List<String> animators = new ArrayList<String>();
            for(int i = 0; i < animatorCount; i++){
                animators.add(movie.findString(("animator" + Integer.toString(i+1))).getString());
            }

            AnimatedMovie movieFinal = new AnimatedMovie(MovieType.ANIMATED, title, director, releaseYear, recommendedAge, animators);
            for(int i = 0; i < ratingCount; i++){
                rateMovie(movieFinal, ratings[i], textRatings[i]);
            }
            movies.add(movieFinal);
            return true;

        } else if(type == 1){
            int actorCount = movie.findField("actorCount").getInt();
            List<String> actors = new ArrayList<String>();
            for(int i = 0; i < actorCount; i++){
                actors.add(movie.findString(("actor" + Integer.toString(i+1))).getString());
            }

            LiveActionMovie movieFinal = new LiveActionMovie(MovieType.LIVE_ACTION, title, director, releaseYear, actors);
            for(int i = 0; i < ratingCount; i++){
                rateMovie(movieFinal, ratings[i], textRatings[i]);
            }
            movies.add(movieFinal);
            return true;
        }
        return false;
    }

    public ArrayList<String> listMovieFilesInFolder(final File folder) {
        int count = 1;
        ArrayList<String> files = new ArrayList<String>();
        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                if(fileEntry.getName().contains(".movie")) {
                    System.out.println("[" + count + "] " + fileEntry.getName());
                    files.add(fileEntry.getName());
                    count++;
                }
            }
        }

        return files;
    }
}
