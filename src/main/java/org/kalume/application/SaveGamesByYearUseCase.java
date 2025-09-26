package org.kalume.application;

import org.kalume.domain.FifaGame;
import org.kalume.infrastructure.CSVFifaGameRepository;

import java.io.IOException;
import java.util.ArrayList;

public class SaveGamesByYearUseCase {

    CSVFifaGameRepository _repository;

    public SaveGamesByYearUseCase(CSVFifaGameRepository _repository) {
        this._repository = _repository;
    }


    public boolean execute(int year) throws IOException {
        ArrayList<FifaGame> dataSet = _repository.retrieveAll();
        ArrayList<FifaGame> filteredSet = new ArrayList<>();

        boolean validationResult = validateYear(year);

        if (validationResult) {
            for (FifaGame fifaGame : dataSet) {
                if (fifaGame.getDate().getYear() == year) {
                    filteredSet.add(fifaGame);
                }
            }
            return saveFilteredSet(filteredSet,year);

        } else {
            return false;

        }

    }

    private boolean saveFilteredSet(ArrayList<FifaGame> filteredSet, int year) throws IOException {
            return _repository.saveMatches(filteredSet,"gamesOf"+ year);

    }

    private boolean validateYear(int year) {

        return year >= 1993 && year <= 2022;
    }


}
