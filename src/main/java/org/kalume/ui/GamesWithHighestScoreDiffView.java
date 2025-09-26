package org.kalume.ui;

import org.kalume.application.GetGamesWithLargestScoreDiffUseCase;
import org.kalume.domain.FifaGame;
import org.kalume.infrastructure.CSVFifaGameRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GamesWithHighestScoreDiffView {
    private final Scanner keyboard;

    public GamesWithHighestScoreDiffView(Scanner keyboard) {
        this.keyboard = keyboard;
    }

    public void init() {
        System.out.println("====== Partidos con Mayor Diferencia de Goles ======");

        GetGamesWithLargestScoreDiffUseCase useCase =
                new GetGamesWithLargestScoreDiffUseCase(new CSVFifaGameRepository("static", "fifa.csv"));

        try {
            ArrayList<FifaGame> games = useCase.execute();

            if (games.isEmpty()) {
                System.out.println("No se encontraron partidos en el dataset.\n");
            } else {
                System.out.println("Partidos con mayor diferencia de goles:\n");
                for (FifaGame game : games) {
                    System.out.println(game.toString());
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Error al leer los datos: " + e.getMessage() + "\n");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage() + "\n");
        }

        System.out.print("Pulse ENTER para volver al menú principal...");
        keyboard.nextLine();
    }
}