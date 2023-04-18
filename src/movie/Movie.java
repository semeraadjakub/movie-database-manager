package movie;

public class Movie {
    protected final String MOVIE_NAME;
    protected final byte MOVIE_RATING;
    protected Movie(String MOVIE_NAME, byte MOVIE_RATING){
        this.MOVIE_NAME = MOVIE_NAME;
        this.MOVIE_RATING = MOVIE_RATING;
    }

    @Override
    public String toString()
    {
        return null;
    }
}
