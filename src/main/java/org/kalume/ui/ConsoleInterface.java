package org.kalume.ui;

import java.util.Scanner;

public class ConsoleInterface {
    final Scanner keyboard;

    public ConsoleInterface() {
        this.keyboard = new Scanner(System.in);
    }

    public void initializeMainMenu() {
        int userChoice = -1;
        do {
            printMenu();
            userChoice = readUserChoice();

            switch (userChoice) {
                case 1:
                    System.out.println("Entrando en guardar partidos favoritos...");
                    SaveFavouriteGameView saveFavouriteGameView = new SaveFavouriteGameView(keyboard);
                    saveFavouriteGameView.init();
                    break;
                case 2:
                    System.out.println("Entrando en filtrar partidos por lugar donde se disputó...");
                    FilterGamesByPlaceView filterGamesByPlaceView =  new FilterGamesByPlaceView(keyboard);
                    filterGamesByPlaceView.init();
                    break;
                case 3:
                    System.out.println("Entrando a filtrar partidos ganados por un equipo nacional...");
                    FilterGamesWonByCountryView wonByInterface = new FilterGamesWonByCountryView(keyboard);
                    wonByInterface.init();
                    break;
                case 4:
                    System.out.println("Entrando a filtrar partidos por año...");
                    FilterGamesByYearView yearInterface = new FilterGamesByYearView(keyboard);
                    yearInterface.init();
                    break;
                case 5:
                    System.out.println("Mostrando partidos...");
                    GamesWithHighestScoreDiffView scoreDiffInterface = new GamesWithHighestScoreDiffView(keyboard);
                    scoreDiffInterface.init();
                    break;
                case 0:
                    finishProgram();
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.\n");
            }
        } while (userChoice != 0);
    }

    private void printMenu() {
        System.out.println("========== Menu principal ==========");
        System.out.println("1. Guardar partidos en favoritos.");
        System.out.println("2. Filtrar y guardar partidos por país donde se disputó.");
        System.out.println("3. Filtrar y guardar partidos por país ganador (local o visitante).");
        System.out.println("4. Filtrar y guardar partidos por año en concreto.");
        System.out.println("5. Mostrar los partidos en los que el ganador haya ganado por mayor diferencia.");
        System.out.println("0. Salir.");
        System.out.print("Escoja la opción deseada: ");
    }

    private int readUserChoice() {
        int choice = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                choice = Integer.parseInt(keyboard.nextLine().trim());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número: ");
            }
        }

        return choice;
    }

    private void finishProgram() {
        System.out.println("\nPrograma finalizado, que pase buen día.");
        keyboard.close();
    }
}
