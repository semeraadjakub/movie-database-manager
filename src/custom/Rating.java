package custom;

public class Rating {
    public Rating(int rating){
        this.rating = rating;
        this.textRating = new String("");
    }

    public Rating(int rating, String textRating){
        this.rating = rating;
        this.textRating = textRating;
    }
    public int rating;
    public String textRating;
}
