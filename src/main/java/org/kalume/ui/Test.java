package org.kalume.ui;

import org.kalume.application.SaveGamesWonByCountryUseCase;
import org.kalume.infrastructure.CSVFifaGameRepository;

import java.io.IOException;

public class Test {

    CSVFifaGameRepository csvFifaGameRepository = new CSVFifaGameRepository("static","fifa.csv");

    public void test(){
        SaveGamesWonByCountryUseCase useCase = new SaveGamesWonByCountryUseCase(csvFifaGameRepository);
        try {
            var result = useCase.execute("Argentina");
            if (result)
                System.out.println("operación exitosa");
            else System.out.println("caca de la vaca");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
