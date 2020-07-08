package com.bridgelabz.IndianCensusAnalyzer.service;

import com.bridgelabz.IndianCensusAnalyzer.exception.CensusAnalyzerException;
import com.bridgelabz.IndianCensusAnalyzer.model.StateCensusDAO;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class StateCensusAnalyzer {
    String JSONFILEPATH = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/main/resources/sortedJson.json";
    List<StateCensusDAO> censusList;

    public enum Country {US, INDIACODE, INDIA}

    public enum SortBy {INDPOPULATION, INDDENSITY, INDAREA, USPOPULOUSSTATE, USDENSITY, USTOTALAREA}
    /**
     * Csv file loader
     *
     * @param country
     * @param CSVPath
     * @return
     * @throws CensusAnalyzerException
     */
    public int loadStateCensusData(Country country, String CSVPath) throws CensusAnalyzerException {
        censusList = CensusAdapterFactory.getCensusData(country, CSVPath);
        return censusList.size();
    }

    /**
     * It will sort by user choice
     * @param sortBy
     * @return
     * @throws IOException
     */
    public int sortByGivenData(StateCensusAnalyzer.SortBy sortBy) throws IOException {
        switch (sortBy) {
            case INDPOPULATION:
                censusList.sort(Comparator.comparing(StateCensusDAO::getPopulation).reversed());
                return createJsonFile(censusList);
            case INDDENSITY:
                censusList.sort(Comparator.comparing(StateCensusDAO::getDensityPerSqKm).reversed());
                return createJsonFile(censusList);
            case INDAREA:
                censusList.sort(Comparator.comparing(StateCensusDAO::getAreaInSqKm).reversed());
                return createJsonFile(censusList);
            case USPOPULOUSSTATE:
                censusList.sort(Comparator.comparing(StateCensusDAO::getUsPopulation).reversed());
                return createJsonFile(censusList);
            case USDENSITY:
                censusList.sort(Comparator.comparing(StateCensusDAO::getUsPopulationDensity).reversed());
                return createJsonFile(censusList);
            case USTOTALAREA:
                censusList.sort(Comparator.comparing(StateCensusDAO::getTotalArea).reversed());
                return createJsonFile(censusList);
            default:
                throw new IllegalStateException("Unexpected value: " + sortBy);
        }
    }

    /**
     * Sort As Per Indian State
     *
     * @return
     * @throws CensusAnalyzerException
     */
    public String getStateWiseSortedCensusData() {
        censusList.sort(Comparator.comparing(StateCensusDAO::getState));
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    /**
     * Sort As per Indian StateCode
     *
     * @return
     * @throws CensusAnalyzerException
     */
    public String getStateCodeWiseSortedCensusData() {
        censusList.sort(Comparator.comparing(StateCensusDAO::getStateCode));
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    /**
     * This method create json file and return  No of of records
     *
     * @param censusLister
     * @return
     * @throws IOException
     */
    public int createJsonFile(List censusLister) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(censusList);
        FileWriter writer = new FileWriter(JSONFILEPATH);
        writer.write(json);
        writer.close();
        return censusLister.size();
    }

    /**
     * It return the populous state with density
     *
     * @param usCSVPath
     * @param indiaCSVPath
     * @return
     * @throws CensusAnalyzerException
     */
    public String givePopulousWithDensityState(String usCSVPath, String indiaCSVPath) throws CensusAnalyzerException {
        this.loadStateCensusData(StateCensusAnalyzer.Country.US, usCSVPath);
        censusList.sort(Comparator.comparing(StateCensusDAO::getUsPopulationDensity).reversed());
        String sortedUSStateCensusJson = new Gson().toJson(censusList);
        StateCensusDAO[] statesCensusCSV1 = new Gson().fromJson(sortedUSStateCensusJson, StateCensusDAO[].class);
        censusList.clear();
        this.loadStateCensusData(Country.INDIA, indiaCSVPath);
        censusList.sort(Comparator.comparing(StateCensusDAO::getDensityPerSqKm).reversed());
        String sortedIndiaCensusJson = new Gson().toJson(censusList);
        StateCensusDAO[] statesCensusCSV2 = new Gson().fromJson(sortedIndiaCensusJson, StateCensusDAO[].class);
        if (statesCensusCSV1[0].usPopulationDensity.compareTo(statesCensusCSV2[0].densityPerSqKm) > 0)
            return statesCensusCSV1[0].usState;
        return statesCensusCSV2[0].State;
    }

}
