import movie.*;
import worker.Actor;
import worker.Animator;
import worker.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static boolean running = true;
    private static final String MENU_STRING =   "[0] Konec\n[1] Přidat nový film\n[2] Smazat film\n[3] Upravit film\n" +
                                                "[4] Vypsat všechny filmy\n[5] Vyhledat informace o filmu\n" +
                                                "[6] Vypsat herce/animátory\n[7] Vyhledat filmy podle herce/animátora\n" +
                                                "[8] Přidat hodnocení\n[9] Uložit informace o filmu do souboru\n" +
                                                "[10] Načíst informmace o filmu ze souboru\n\n > ";
    public static void main(String[] args){
        //TODO: načtení informací z databáze
        Scanner scanner = new Scanner(System.in);

        byte option;
        MovieManager manager = new MovieManager();

        while(running) {
            System.out.print(MENU_STRING);
            option = scanner.nextByte();
            scanner.nextLine();
            switch(option){
                case 0:
                    running = false;
                    break;
                case 1:
                    System.out.print("Zadejte název filmu: ");
                    String movieTitle = scanner.nextLine();
                    System.out.print("Je film animovaný?(y/N): ");
                    MovieType movieType;
                    if(scanner.nextLine().equalsIgnoreCase("y")) {
                        movieType = MovieType.ANIMATED;
                    }
                    else{
                        movieType = MovieType.LIVE_ACTION;
                    }
                    System.out.print("Zadejte jméno režiséra: ");
                    String directorName = scanner.nextLine();
                    System.out.print("Zadejte rok vydání: ");
                    int releaseYear = scanner.nextInt();
                    scanner.nextLine();
                    int recommendedAge = 0;
                    if(movieType == MovieType.ANIMATED){
                        System.out.print("Zadejte doporučený věk diváka: ");
                        recommendedAge = scanner.nextInt();
                        scanner.nextLine();
                    }

                    System.out.print("Přejete si uvést seznam " + (movieType == MovieType.ANIMATED ? "animátorů?" : "herců?") + "(y/N): ");
                    if(scanner.nextLine().equalsIgnoreCase("y")){
                        System.out.println("\nPro ukončení nechte řádek prázdný");
                        List<String> workerNames = new ArrayList<String>();
                        String workerType = (movieType == MovieType.ANIMATED ? "animátora" : "herce");
                        String workerName;
                        int count = 1;
                        System.out.print("Jméno " + workerType + " " + count + ": ");
                        workerName = scanner.nextLine();

                        while(!workerName.equals("")){
                            count++;
                            workerNames.add(workerName);
                            System.out.print("Jméno " + workerType + " " + count + ": ");
                            workerName = scanner.nextLine();
                        }

                        if(movieType == MovieType.ANIMATED)
                            manager.addMovie(movieType, movieTitle, directorName, releaseYear, recommendedAge, workerNames);
                        else if (movieType == MovieType.LIVE_ACTION)
                            manager.addMovie(movieType, movieTitle, directorName, releaseYear, workerNames);
                    } else {
                        if(movieType == MovieType.ANIMATED)
                            manager.addMovie(movieType, movieTitle, directorName, releaseYear, recommendedAge);
                        else if (movieType == MovieType.LIVE_ACTION)
                            manager.addMovie(movieType, movieTitle, directorName, releaseYear);
                    }

                    break;
                case 2:

                    System.out.println("\nSeznam filmů v databázi:");
                    for(Movie movie : manager.getMovies()){
                        System.out.println(movie.getTitle());
                    }

                    System.out.print("\nNázev filmu k odstranění: ");
                    manager.deleteMovieByTitle(scanner.nextLine());
                    break;
                case 3:

                    break;
                case 4:
                    System.out.println();
                    for(Movie movie : manager.getMovies()){
                        System.out.println("Název: " + movie.getTitle());
                        System.out.println("Druh filmu: " + (movie.getType() == MovieType.ANIMATED ? "Animovaný" : "Hraný"));
                        System.out.println("Režisér: " + movie.getDirector());
                        System.out.println("Rok vydání: " + movie.getReleaseYear());
                        if(movie.getType() == MovieType.ANIMATED){
                            System.out.println("Doporučený věk diváka: " + ((AnimatedMovie)(movie)).getRecommendedAge());
                            ArrayList<Animator> animators = ((AnimatedMovie)(movie)).getAnimatorList();
                            if(animators != null){
                                System.out.println("Animátoři: ");
                                for(Animator animator : animators){
                                    System.out.println("    " + animator.getName());
                                }
                            } else {
                                System.out.println("Animátoři neuvedeni.");
                            }
                        } else {
                            ArrayList<Actor> actors = ((LiveActionMovie)(movie)).getActorList();
                            if(actors != null){
                                System.out.println("Herci: ");
                                for(Actor actor : actors){
                                    System.out.println("    " + actor.getName());
                                }
                            } else {
                                System.out.println("Herci neuvedeni.");
                            }
                        }
                        System.out.println();
                    }
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                default:
                    break;
            }
        }

        //TODO: uložení informací (stavu) do databáze
        scanner.close();

    }
}
