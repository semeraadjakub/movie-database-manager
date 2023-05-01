package movie;

public class Movie {
    MovieType type;
    protected String title;
    protected String director;
    protected int releaseYear;

    protected Movie(MovieType type, String title, String director, int releaseYear) {
        this.type = type;
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
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
}
