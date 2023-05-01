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

    public ArrayList<Actor> getActorList(){
        return actors;
    }
}
