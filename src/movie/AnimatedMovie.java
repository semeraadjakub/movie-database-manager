package movie;

import custom.Rating;
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

    private int getAnimatorIndex(String name){
        for(int i = 0; i < animators.size(); i++)
            if(animators.get(i).getName().equalsIgnoreCase(name))
                return i;

        return -1;
    }

    public boolean deleteAnimator(String name){
        int index = getAnimatorIndex(name);
        if(index != -1){
            animators.remove(index);
            return true;
        }
        return false;
    }

    public boolean modifyAnimatorName(String name){
        int index = getAnimatorIndex(name);
        if(index != -1){
            animators.get(index).setName(name);
            return true;
        }
        return false;
    }

    public ArrayList<Animator> getAnimatorList(){
        return animators;
    }
}
