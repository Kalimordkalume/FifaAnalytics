package org.kalume.ui;

import org.kalume.application.FetchCountryNamesUseCase;
import org.kalume.application.SaveGamesByCountryPlayedUseCase;
import org.kalume.infrastructure.CSVFifaGameRepository;

import java.io.IOException;
import java.util.Scanner;

public class FilterGamesByPlaceView {
    private final Scanner keyboard;

    public FilterGamesByPlaceView(Scanner keyboard) {
        this.keyboard = keyboard;
    }

    public void init() {
        System.out.println("====== Filtrar Partidos por País ======");

        CSVFifaGameRepository repository = new CSVFifaGameRepository("static","fifa.csv");
        FetchCountryNamesUseCase countriesUseCase = new FetchCountryNamesUseCase(repository);

        try {
            var countrySet = countriesUseCase.execute();

            while (true) {
                System.out.print("Ingrese el país para filtrar los partidos (o escriba 0 para volver): ");
                String country = keyboard.nextLine().trim();

                if (country.equals("0")) {
                    System.out.println("Volviendo al menú principal...\n");
                    return;
                }

                if (!countrySet.contains(country.toLowerCase())) {
                    System.out.println("El país ingresado no existe en el dataset. Intente de nuevo.\n");
                    continue;
                }

                SaveGamesByCountryPlayedUseCase useCase =
                        new SaveGamesByCountryPlayedUseCase(repository);

                boolean success = useCase.execute(country);
                if (success) {
                    System.out.println("Archivo generado correctamente: " + country + ".csv\n");
                } else {
                    System.out.println("No se encontraron partidos en el país indicado.\n");
                }

                System.out.println("Operación finalizada. Pulse ENTER para volver al menú principal...");
                keyboard.nextLine();
                return;
            }

        } catch (IOException e) {
            System.out.println("Error al generar el archivo: " + e.getMessage() + "\n");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage() + "\n");
        }
    }
}
