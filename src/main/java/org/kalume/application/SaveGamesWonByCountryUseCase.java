package org.kalume.application;

import org.kalume.domain.FifaGame;
import org.kalume.infrastructure.CSVFifaGameRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class SaveGamesWonByCountryUseCase {

    public CSVFifaGameRepository csvFifaGameRepository;

    public SaveGamesWonByCountryUseCase(CSVFifaGameRepository csvFifaGameRepository) {
        this.csvFifaGameRepository = csvFifaGameRepository;
    }

    public boolean execute(String country) throws IOException {
        FetchCountryNamesUseCase countriesUseCase = new FetchCountryNamesUseCase(csvFifaGameRepository);
        HashSet<String> countrySet = countriesUseCase.execute();

        ArrayList<FifaGame> filteredSet = new ArrayList<>(0);
        if (countrySet.contains(country.toLowerCase())) {
            ArrayList<FifaGame> gamesDataSet = csvFifaGameRepository.retrieveAll();
            for (FifaGame fifaGame : gamesDataSet) {
                boolean condition1 = country.equalsIgnoreCase(fifaGame.getLocalTeam()) || country.equalsIgnoreCase(fifaGame.getVisitorTeam());
                boolean condition2 = country.equalsIgnoreCase(fifaGame.wonBy());
                if (condition1 && condition2) {
                    filteredSet.add(fifaGame);
                }
            }
        }

        return csvFifaGameRepository.saveMatches(filteredSet,"wonBy"+country);
    }

}
