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

    /**
     * Csv file loader
     * @param country
     * @param CSVPath
     * @return
     * @throws CensusAnalyzerException
     */
    public int loadStateCensusData(Country country, String CSVPath) throws CensusAnalyzerException {
        censusList = new CensusLoader().loadAllCensusData(country, CSVPath);
        return censusList.size();
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
     * Sort As Per DensityPerSqKM
     *
     * @return
     * @throws CensusAnalyzerException
     */
    public int getPopulousStateWiseSortedCensusData() throws IOException {
        censusList.sort(Comparator.comparing(StateCensusDAO::getPopulation).reversed());
        return createJsonFile(censusList);
    }

    /**
     * Sort as Population Density
     *
     * @return
     * @throws CensusAnalyzerException
     */
    public int getPopulationDensityStateWiseSortedCensusData() throws IOException {
        censusList.sort(Comparator.comparing(StateCensusDAO::getDensityPerSqKm).reversed());
        return createJsonFile(censusList);
    }

    /**
     * Sort as per Area
     *
     * @return
     * @throws CensusAnalyzerException
     */
    public int getAreaWiseWiseSortedStateCensusData() throws IOException {
        censusList.sort(Comparator.comparing(StateCensusDAO::getAreaInSqKm).reversed());
        return createJsonFile(censusList);
    }

    /**
     * Sort Based on Us Population
     *
     * @return
     * @throws IOException
     */
    public int getUsPopulousStateWiseSortedCensusData() throws IOException {
        censusList.sort(Comparator.comparing(StateCensusDAO::getUsPopulation).reversed());
        return createJsonFile(censusList);
    }

    /**
     * Sorted order based on usPopulationDensity
     *
     * @return
     * @throws IOException
     */
    public int getUsPopulationDensityStateWiseSortedCensusData() throws IOException {
        censusList.sort(Comparator.comparing(StateCensusDAO::getUsPopulationDensity).reversed());
        return createJsonFile(censusList);
    }

    /**
     * Sorted order based on totalArea
     *
     * @return
     * @throws IOException
     */
    public int getUsTotalAreaStateWiseSortedCensusData() throws IOException {
        censusList.sort(Comparator.comparing(StateCensusDAO::getTotalArea).reversed());
        return createJsonFile(censusList);
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
