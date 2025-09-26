package org.kalume.application;

import org.kalume.domain.FifaGame;
import org.kalume.infrastructure.CSVFifaGameRepository;

import java.io.IOException;
import java.util.ArrayList;

public class GetGamesWithLargestScoreDiffUseCase {
    CSVFifaGameRepository csvFifaGameRepository;


    public GetGamesWithLargestScoreDiffUseCase(CSVFifaGameRepository csvFifaGameRepository) {
        this.csvFifaGameRepository = csvFifaGameRepository;
    }


    public ArrayList<FifaGame> execute() throws IOException {

        ArrayList<FifaGame> dataSet = csvFifaGameRepository.retrieveAll();

        int highestDifference = getHighestDifference(dataSet);
        ArrayList<FifaGame> filteredSet = new ArrayList<>(0);

        for (FifaGame fifaGame : dataSet) {
            if (fifaGame.scoreDifference() == highestDifference) {
                filteredSet.add(fifaGame);
            }
        }

        return filteredSet;
    }

    private int getHighestDifference(ArrayList<FifaGame> dataSet) {

        int difference = 0;
        for (FifaGame fifaGame : dataSet) {
            if (fifaGame.scoreDifference() > difference) {
                difference = fifaGame.scoreDifference();
            }


        }
        return difference;
    }
}
