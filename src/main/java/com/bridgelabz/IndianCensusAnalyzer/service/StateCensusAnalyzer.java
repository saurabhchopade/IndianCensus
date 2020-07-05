package com.bridgelabz.IndianCensusAnalyzer.service;

import com.bridgelabz.IndianCensusAnalyzer.exception.CensusAnalyzerException;
import com.bridgelabz.IndianCensusAnalyzer.model.StateCensusCodeCSV;
import com.bridgelabz.IndianCensusAnalyzer.model.StateCensusDAO;
import com.bridgelabz.IndianCensusAnalyzer.model.StatesCensusCSV;
import com.bridgelabz.IndianCensusAnalyzer.model.UsCensusCSV;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StateCensusAnalyzer {
    String JSONFILEPATH = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/main/resources/sortedJson.json";
    List<StateCensusDAO> censusList;
    /**
     * To Load The State Census Csv File
     *
     * @param StateCsvPath
     * @return
     * @throws CensusAnalyzerException
     */
    public int loadStateCensusData(String StateCsvPath) throws CensusAnalyzerException {
        censusList = new CensusLoader().loadCensusData(StateCsvPath, StatesCensusCSV.class);
        return censusList.size();
    }

    /**
     * To Load the StateCode CSV File
     *
     * @param StateCodeCsvPath
     * @return
     * @throws CensusAnalyzerException
     */
    public int loadStateCensusCodeData(String StateCodeCsvPath) throws CensusAnalyzerException {
        censusList = new CensusLoader().loadCensusData(StateCodeCsvPath, StateCensusCodeCSV.class);
        return censusList.size();
    }

    /**
     * To Load the StateCode CSV File
     *
     * @param StateCodeCsvPath
     * @return
     * @throws CensusAnalyzerException
     */
    public int loadUsCensusData(String StateCodeCsvPath) throws CensusAnalyzerException {
        censusList = new CensusLoader().loadCensusData(StateCodeCsvPath, UsCensusCSV.class);
        return censusList.size();
    }


    /**
     * Sort As Per State
     *
     * @return
     * @throws CensusAnalyzerException
     */
    public int getStateWiseSortedCensusData() throws IOException {
        censusList.sort((firstElement, secondElement) -> secondElement.State.compareTo(firstElement.State));
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return createJsonFile(censusList, sortedStateCensusJson);
    }

    /**
     * Sort As per StateCode
     *
     * @return
     * @throws CensusAnalyzerException
     */
    public String getStateCodeWiseSortedCensusData() {
        censusList.sort((firstElement, secondElement) -> secondElement.stateCode.compareTo(firstElement.stateCode));
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
        censusList.sort((firstElement, secondElement) -> secondElement.population.compareTo(firstElement.population));
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return createJsonFile(censusList, sortedStateCensusJson);
    }

    /**
     * Sort as Population Density
     *
     * @return
     * @throws CensusAnalyzerException
     */
    public int getPopulationDensityStateWiseSortedCensusData() throws IOException {
        censusList.sort((firstElement, secondElement) -> secondElement.densityPerSqKm.compareTo(firstElement.densityPerSqKm));
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return createJsonFile(censusList, sortedStateCensusJson);
    }

    /**
     * Sort as per Area
     *
     * @return
     * @throws CensusAnalyzerException
     */
    public int getAreaWiseWiseSortedStateCensusData() throws IOException {
        censusList.sort((firstElement, secondElement) -> secondElement.areaInSqKm.compareTo(firstElement.areaInSqKm));
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return createJsonFile(censusList, sortedStateCensusJson);
    }

    /**
     * Sort Based on Us Population
     *
     * @return
     * @throws IOException
     */
    public int getUsPopulousStateWiseSortedCensusData() throws IOException {
        censusList.sort((firstElement, secondElement) -> secondElement.usPopulation.compareTo(firstElement.usPopulation));
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return createJsonFile(censusList, sortedStateCensusJson);
    }

    /**
     * Sorted order based on usPopulationDensity
     *
     * @return
     * @throws IOException
     */
    public int getUsPopulationDensityStateWiseSortedCensusData() throws IOException {
        censusList.sort((firstElement, secondElement) -> secondElement.usPopulationDensity.compareTo(firstElement.usPopulationDensity));
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return createJsonFile(censusList, sortedStateCensusJson);
    }

    /**
     * Sorted order based on totalArea
     *
     * @return
     * @throws IOException
     */
    public int getUsTotalAreaStateWiseSortedCensusData() throws IOException {
        censusList.sort((firstElement, secondElement) -> secondElement.totalArea.compareTo(firstElement.totalArea));
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return createJsonFile(censusList, sortedStateCensusJson);
    }

    /**
     * Here we create Json file for each time
     *
     * @param censusLister
     * @param sortedJsonData
     * @return
     * @throws IOException
     */
    public int createJsonFile(List censusLister, String sortedJsonData) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(sortedJsonData);
        FileWriter writer = new FileWriter(JSONFILEPATH);
        writer.write(json);
        writer.close();
        return censusLister.size();
    }
}
