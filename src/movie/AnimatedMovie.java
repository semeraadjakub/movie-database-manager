package movie;

import worker.Animator;

import java.util.ArrayList;
import java.util.List;

public class AnimatedMovie extends Movie{

    private ArrayList<Animator> animators = null;
    private int recommendedAge;
    public AnimatedMovie(MovieType type, String title, String director, int releaseYear, int recommendedAge) {
        super(type, title, director, releaseYear);
        this.recommendedAge = recommendedAge;
    }

    public AnimatedMovie(MovieType type, String title, String director, int releaseYear, int recommendedAge, List<String> animatorNames) {
        super(type, title, director, releaseYear);
        this.recommendedAge = recommendedAge;

        animators = new ArrayList<Animator>();
        for(String animator : animatorNames)
            animators.add(new Animator(animator));
    }

    public int getRecommendedAge(){
        return recommendedAge;
    }

    public void setRecommendedAge(int recommendedAge){
        this.recommendedAge = recommendedAge;
    }

    public ArrayList<Animator> getAnimatorList(){
        return animators;
    }
}
