package org.kalume.ui;

import org.kalume.application.SaveGamesByYearUseCase;
import org.kalume.infrastructure.CSVFifaGameRepository;

import java.io.IOException;
import java.util.Scanner;

public class FilterGamesByYearView {
    private final Scanner keyboard;

    public FilterGamesByYearView(Scanner keyboard) {
        this.keyboard = keyboard;
    }

    public void init() {
        System.out.println("====== Filtrar Partidos por Año ======");
        System.out.print("Ingrese el año (1993 - 2022) o 0 para volver: ");

        int year;
        try {
            year = Integer.parseInt(keyboard.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Debe ingresar un número.\n");
            return;
        }

        if (year == 0) {
            System.out.println("Volviendo al menú principal...\n");
            return;
        }


        if (year < 1993 || year > 2022) {
            System.out.println("El año debe estar entre 1993 y 2022.\n");
            return;
        }

        SaveGamesByYearUseCase useCase =
                new SaveGamesByYearUseCase(new CSVFifaGameRepository("static", "fifa.csv"));

        try {
            boolean success = useCase.execute(year);
            if (success) {
                System.out.println("Archivo generado correctamente: gamesOf" + year + ".csv\n");
            } else {
                System.out.println("No se encontraron partidos para el año indicado.\n");
            }
        } catch (IOException e) {
            System.out.println("Error al generar el archivo: " + e.getMessage() + "\n");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage() + "\n");
        }
    }
}