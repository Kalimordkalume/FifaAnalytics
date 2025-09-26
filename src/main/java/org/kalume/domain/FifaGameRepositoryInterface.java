package org.kalume.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FifaGameRepositoryInterface {
    List<FifaGame> retrieveAll() throws FileNotFoundException, IOException;
    boolean addNewFavouriteMatch(FifaGame fifaGame) throws IOException;
    boolean saveMatches(List<FifaGame> matches, String fileName) throws IOException;

}
