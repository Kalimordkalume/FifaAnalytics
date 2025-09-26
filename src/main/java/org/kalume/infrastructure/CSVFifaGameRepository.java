package org.kalume.infrastructure;

import org.kalume.domain.FifaGame;
import org.kalume.domain.FifaGameRepositoryInterface;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVFifaGameRepository implements FifaGameRepositoryInterface {
    private String folderName;
    private String sourceFileName;

    public CSVFifaGameRepository(String folderName, String sourceFileName) {
        this.folderName = folderName;
        this.sourceFileName = sourceFileName;
    }

    @Override
    public ArrayList<FifaGame> retrieveAll() throws FileNotFoundException, IOException {
        ArrayList<FifaGame> output = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(buildPathName()))) {

            br.readLine();

            String dataLine;
            while ((dataLine = br.readLine()) != null) {
                String[] formattedData = dataLine.split(",");

                if (formattedData.length < 14) {
                    continue;
                }

                try {

                    FifaGame fifaGame = buildFromParsedData(formattedData);
                    output.add(fifaGame);
                } catch (Exception e) {
                }
            }
        }

        return output;
    }


    @Override
    public boolean addNewFavouriteMatch(FifaGame fifaGame) throws IOException {
        String outputFile = "myFavourites.csv";
        String pathName = folderName + File.separator + outputFile;
        boolean succes = false;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathName,true))) {
            bw.write(fifaGame.parseToCsvFormat());
            bw.newLine();
            succes = true;
            return succes;
        }
    }

    @Override
    public boolean saveMatches(List<FifaGame> matches, String fileName) throws IOException {
        String outputFile = fileName;
        String extension = ".csv";
        String pathName = folderName + File.separator + outputFile + extension;

        BufferedWriter bw = new BufferedWriter(new FileWriter(pathName));

        for (FifaGame match : matches) {
            bw.write(match.parseToCsvFormat());
            bw.newLine();
        }

        bw.close();
        return true;


    }

    private String buildPathName(){
        return folderName + File.separator + sourceFileName;

    }


    private FifaGame buildFromParsedData(String[] parsedData) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(parsedData[0],df);
        String localTeam = parsedData[1];
        String visitorTeam = parsedData[2];
        int localScore = Integer.parseInt(parsedData[9]);
        int visitorScore = Integer.parseInt(parsedData[10]);
        String tournamentName = parsedData[11];
        String cityName = parsedData[12];
        String countryName = parsedData[13];

        return new FifaGame(date,localTeam,visitorTeam,localScore,visitorScore,tournamentName,cityName,countryName);

    }

    private String[] parseDataLineToFifaMatchFormat(String dataLine)
    {
        String[] output = new String[8];
        String[] formattedData = dataLine.split(",");

        output[0] = formattedData[0];
        output[1] = formattedData[1];
        output[2] = formattedData[2];
        output[3] = formattedData[9];
        output[4] = formattedData[10];
        output[5] = formattedData[11];
        output[6] = formattedData[12];
        output[7] = formattedData[13];

        return output;
    }
}
