import movie.AnimatedMovie;
import movie.LiveActionMovie;

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

        while(running) {
            System.out.print(MENU_STRING);
            option = scanner.nextByte();
            switch(option){
                case 0:
                    running = false;
                    break;
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
            }
        }

        //TODO: uložení informací (stavu) do databáze
        scanner.close();

    }
}
