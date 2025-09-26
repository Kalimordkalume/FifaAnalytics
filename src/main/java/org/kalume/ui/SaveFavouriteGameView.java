package org.kalume.ui;

import org.kalume.application.SaveFavouriteGameUseCase;
import org.kalume.infrastructure.CSVFifaGameRepository;

import java.io.IOException;
import java.util.Scanner;

public class SaveFavouriteGameView {
    private final Scanner keyboard;

    public SaveFavouriteGameView(Scanner keyboard) {
        this.keyboard = keyboard;
    }

    public void init() {
        int userChoice = -1;
        do {
            printScreen();
            userChoice = readUserChoice();

            switch (userChoice) {
                case 0:
                    System.out.println("Volviendo al menú principal...\n");
                    return;
                default:
                    if (userChoice > 0) {
                        System.out.println("Guardando el partido favorito con índice: " + userChoice + "\n");
                        SaveFavouriteGameUseCase useCase = new SaveFavouriteGameUseCase(new CSVFifaGameRepository("static","fifa.csv"));
                        executeUserCase(userChoice-1,useCase);
                    } else {
                        System.out.println("Entrada inválida. Ingrese un número mayor que cero o 0 para salir.\n");
                    }
            }
        } while (true);
    }

    private void executeUserCase(int userChoice, SaveFavouriteGameUseCase useCase) {
        try {
            boolean success = useCase.execute(userChoice);
            if (success) {
                System.out.println("Partido guardado correctamente.\n");
            } else {
                System.out.println("No se pudo guardar el partido. Verifique que el índice sea válido.\n");
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar el partido: " + e.getMessage() + "\n");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage() + "\n");
        }
    }

    private void printScreen() {
        System.out.println("====== Guardar Partidos Favoritos ======");
        System.out.println("Ingrese el número del partido que desea guardar como favorito.");
        System.out.println("0. Volver al menú principal.");
        System.out.print("Su elección: ");
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
}
