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

    private int getAnimatorIndex(String name){
        for(int i = 0; i < animators.size(); i++)
            if(animators.get(i).getName().equalsIgnoreCase(name))
                return i;

        return -1;
    }

    public boolean deleteAnimator(int index){
        if(index >= 0 && index < animators.size())
        {
            animators.remove(index);
            return true;
        }

        return false;
    }

    public boolean modifyAnimatorName(String newName, int index){
        if(index >= 0 && index < animators.size())
        {
            animators.get(index).setName(newName);
            return true;
        }

        return false;
    }

    public ArrayList<Animator> getAnimatorList(){
        return animators;
    }
    public ArrayList<String> getAnimatorListString(){
        ArrayList<String> animators1 = new ArrayList<String>();
        for(Animator a : animators){
            animators1.add(a.getName());
        }
        return animators1;
    }
}
