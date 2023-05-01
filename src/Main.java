import movie.*;

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
            Movie movie;
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
                    manager.printMovieTitles();
                    System.out.print("\nNázev filmu k odstranění: ");
                    manager.deleteMovieByTitle(scanner.nextLine());
                    break;
                case 3:
                    manager.printMovieTitles();
                    System.out.print("\nNázev filmu k úpravě: ");
                    movie = manager.getMovieByTitle(scanner.nextLine());
                    if(movie != null){
                        System.out.println("\n[1] úprava názvu\n[2] změnit jméno režiséra\n[3] změnit rok vydání\n[4] upravit seznam " + (movie.getType() == MovieType.ANIMATED ? "animátorů" : "herců") + (movie.getType() == MovieType.ANIMATED ? "\n[5] změnit doporučený věk" : ""));
                        option = scanner.nextByte();
                        scanner.nextLine();

                        switch(option){
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                break;
                            case 5:
                                break;
                            default:
                                break;
                        }
                    } else {
                        System.out.println("Film není v databázi!");
                    }
                    break;
                case 4:
                    System.out.println();
                    manager.printAllMovies();
                    break;
                case 5:
                    manager.printMovieTitles();
                    System.out.print("\nVyhledat informace o: ");
                    movie = manager.getMovieByTitle(scanner.nextLine());
                    if(movie != null){
                        manager.printMovie(movie, true);
                    }
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    manager.printMovieTitles();
                    System.out.print("\nPřidat hodnocení k: ");
                    movie = manager.getMovieByTitle(scanner.nextLine());
                    if(movie != null) {
                        int rating = 0;
                        if(movie.getType() == MovieType.ANIMATED){
                            System.out.print("Zadejte hodnocení(1-10): ");
                            rating = scanner.nextInt();
                        } else {
                            System.out.print("Zadejte hodnocení(1-5): ");
                            rating = scanner.nextInt();
                        }
                        scanner.nextLine();
                        System.out.print("Přejete si přidat slovní ohodnocení?(y/N): ");
                        if(scanner.nextLine().equalsIgnoreCase("y")){
                            System.out.print("Slovní hodnocení: ");
                            manager.rateMovie(movie, rating, scanner.nextLine());
                        } else {
                            manager.rateMovie(movie, rating);
                        }

                    } else {
                        System.out.println("Film není v databázi!");
                    }
                    break;
                case 9:
                    break;
                case 10:
                    break;
                default:
                    System.out.println("Neplatná volba!");
                    break;
            }
        }

        //TODO: uložení informací (stavu) do databáze
        scanner.close();

    }
}
