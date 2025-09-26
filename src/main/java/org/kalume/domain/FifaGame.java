package org.kalume.domain;

import java.time.LocalDate;

public class FifaGame {
    private LocalDate date;
    private String localTeam;
    private String visitorTeam;
    private int localScore;
    private int visitorScore;
    private String tournamentName;
    private String cityName;
    private String countryName;

    public FifaGame(LocalDate date, String localTeam, String visitorTeam,
                    int localScore, int visitorScore,
                    String tournamentName, String cityName, String countryName) {
        this.date = date;
        this.localTeam = localTeam;
        this.visitorTeam = visitorTeam;
        this.localScore = localScore;
        this.visitorScore = visitorScore;
        this.tournamentName = tournamentName;
        this.cityName = cityName;
        this.countryName = countryName;
    }

    public String wonBy(){
        if (localScore > visitorScore)
            return localTeam;
        if (localScore < visitorScore)
            return visitorTeam;
        return "empate";
    }

    public int scoreDifference() {
        return Math.abs(localScore - visitorScore);
    }

    public String parseToCsvFormat() {
        return String.join(",",
                date.toString(),
                localTeam,
                visitorTeam,
                String.valueOf(localScore),
                String.valueOf(visitorScore),
                tournamentName,
                cityName,
                countryName
        );
    }

    @Override
    public String toString() {
        return "FifaGame {\n" +
                "  Fecha: " + date + ",\n" +
                "  Equipo local: " + localTeam + " (" + localScore + "),\n" +
                "  Equipo visitante: " + visitorTeam + " (" + visitorScore + "),\n" +
                "  Torneo: " + tournamentName + ",\n" +
                "  Ciudad: " + cityName + ",\n" +
                "  País: " + countryName + "\n" +
                "}";
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public int getVisitorScore() {
        return visitorScore;
    }

    public void setVisitorScore(int visitorScore) {
        this.visitorScore = visitorScore;
    }

    public int getLocalScore() {
        return localScore;
    }

    public void setLocalScore(int localScore) {
        this.localScore = localScore;
    }

    public String getVisitorTeam() {
        return visitorTeam;
    }

    public void setVisitorTeam(String visitorTeam) {
        this.visitorTeam = visitorTeam;
    }

    public String getLocalTeam() {
        return localTeam;
    }

    public void setLocalTeam(String localTeam) {
        this.localTeam = localTeam;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}