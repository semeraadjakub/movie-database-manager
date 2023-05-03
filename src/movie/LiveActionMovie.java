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

    public boolean deleteActor(int index){
        if(index >= 0 && index < actors.size()){
            actors.remove(index);
            return true;
        }
        return false;
    }

    public boolean modifyActorName(String newName, int index){
        if(index >= 0 && index < actors.size()){
            actors.get(index).setName(newName);
            return true;
        }
        return false;
    }

    public ArrayList<Actor> getActorList(){
        return actors;
    }

    public ArrayList<String> getActorListString(){
        ArrayList<String> actors1 = new ArrayList<String>();
        for(Actor a : actors){
            actors1.add(a.getName());
        }
        return actors1;
    }
}
