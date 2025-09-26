package org.kalume.application;

import org.kalume.domain.FifaGame;
import org.kalume.infrastructure.CSVFifaGameRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class SaveGamesByCountryPlayedUseCase {


    CSVFifaGameRepository csvFifaGameRepository;

    public SaveGamesByCountryPlayedUseCase(CSVFifaGameRepository csvFifaGameRepository) {
        this.csvFifaGameRepository = csvFifaGameRepository;
    }

    public boolean execute(String country) throws IOException {
        FetchCountryNamesUseCase countriesUseCase = new FetchCountryNamesUseCase(csvFifaGameRepository);
        HashSet<String> countrySet = countriesUseCase.execute();

        ArrayList<FifaGame> filteredSet = new ArrayList<>(0);


        if (countrySet.contains(country.toLowerCase())) {
            ArrayList<FifaGame> gamesDataSet = csvFifaGameRepository.retrieveAll();
            for (FifaGame fifaGame : gamesDataSet) {
                if (fifaGame.getCountryName().equalsIgnoreCase(country)) {
                    filteredSet.add(fifaGame);
                }
            }
        }

        return csvFifaGameRepository.saveMatches(filteredSet,country);
    }



}
