package movie;

import custom.Rating;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    MovieType type;
    protected String title;
    protected String director;
    protected int releaseYear;
    protected ArrayList<Rating> ratings;

    protected Movie(MovieType type, String title, String director, int releaseYear) {
        this.type = type;
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        ratings = new ArrayList<Rating>();
    }

    public String getTitle(){
        return title;
    }

    public String getDirector(){
        return director;
    }

    public MovieType getType(){
        return type;
    }

    public int getReleaseYear(){
        return releaseYear;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDirector(String director){
        this.director = director;
    }

    public void setReleaseYear(int releaseYear){
        this.releaseYear = releaseYear;
    }
    public ArrayList<Rating> getRatings(){
        return ratings;
    }

    public boolean rate(int rating){
        if(this.type == MovieType.ANIMATED) {
            if (rating >= 1 && rating <= 10) {
                this.ratings.add(new Rating(rating));
                return true;
            }
        } else {
            if (rating >= 1 && rating <= 5) {
                this.ratings.add(new Rating(rating));
                return true;
            }
        }
        return false;
    }

    public boolean rate(int rating, String textRating){
        if(this.type == MovieType.ANIMATED) {
            if (rating >= 1 && rating <= 10) {
                this.ratings.add(new Rating(rating, textRating));
                return true;
            }
        } else {
            if (rating >= 1 && rating <= 5) {
                this.ratings.add(new Rating(rating, textRating));
                return true;
            }
        }
        return false;
    }
}
