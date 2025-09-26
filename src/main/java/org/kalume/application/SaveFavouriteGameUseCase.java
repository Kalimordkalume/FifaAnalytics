package org.kalume.application;

import org.kalume.domain.FifaGame;
import org.kalume.infrastructure.CSVFifaGameRepository;

import java.io.IOException;


public class SaveFavouriteGameUseCase {
    CSVFifaGameRepository csvFifaGameRepository;
    public SaveFavouriteGameUseCase(CSVFifaGameRepository csvFifaGameRepository) {
        this.csvFifaGameRepository = csvFifaGameRepository;
    }

    public FifaGame getFifaGameById(int id) throws IOException{
        var games = csvFifaGameRepository.retrieveAll();

        for (int i = 0; i < games.size(); i++) {
            if (i == id) {
                return games.get(i);
            }
        }
        return null;
    }

    public boolean execute(int gameIndex) throws IOException {
        return csvFifaGameRepository.addNewFavouriteMatch(getFifaGameById(gameIndex));
    }
}
