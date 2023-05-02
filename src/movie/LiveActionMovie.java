package movie;

import custom.Rating;
import worker.Actor;
import worker.Animator;

import java.util.ArrayList;
import java.util.List;

public class LiveActionMovie extends Movie{
    private ArrayList<Actor> actors = null;

    public LiveActionMovie(MovieType type, String title, String director, int releaseYear) {
        super(type, title, director, releaseYear);
    }

    public LiveActionMovie(MovieType type, String title, String director, int releaseYear, List<String> actorNames) {
        super(type, title, director, releaseYear);
        actors = new ArrayList<Actor>();

        for(String actor : actorNames)
            actors.add(new Actor(actor));
    }

    private int getActorIndex(String name){
        for(int i = 0; i < actors.size(); i++)
            if(actors.get(i).getName().equalsIgnoreCase(name))
                return i;

        return -1;
    }

    public boolean deleteActor(String name){
        int index = getActorIndex(name);
        if(index != -1){
            actors.remove(index);
            return true;
        }
        return false;
    }

    public boolean modifyActorName(String name){
        int index = getActorIndex(name);
        if(index != -1){
            actors.get(index).setName(name);
            return true;
        }
        return false;
    }

    public ArrayList<Actor> getActorList(){
        return actors;
    }
}
