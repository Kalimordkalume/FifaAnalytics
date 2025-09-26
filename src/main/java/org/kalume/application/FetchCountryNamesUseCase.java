package org.kalume.application;

import org.kalume.domain.FifaGame;
import org.kalume.infrastructure.CSVFifaGameRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class FetchCountryNamesUseCase {

    CSVFifaGameRepository csvFifaGameRepository;

    public FetchCountryNamesUseCase(CSVFifaGameRepository csvFifaGameRepository) {
        this.csvFifaGameRepository = csvFifaGameRepository;
    }

    public HashSet<String> execute() throws IOException {
        return getCountriesFromDataSet();

    }

    private HashSet<String> getCountriesFromDataSet() throws IOException {
        HashSet<String> output = new HashSet<>();
        ArrayList<FifaGame> dataSet = csvFifaGameRepository.retrieveAll();

        for (FifaGame fifaGame : dataSet) {
            String extractedCountry = fifaGame.getCountryName().toLowerCase();
            output.add(extractedCountry);

        }
        return output;

    }
}
