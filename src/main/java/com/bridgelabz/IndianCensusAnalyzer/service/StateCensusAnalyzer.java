package com.bridgelabz.IndianCensusAnalyzer.service;

import com.bridgelabz.IndianCensusAnalyzer.exception.CensusAnalyzerException;
import com.bridgelabz.IndianCensusAnalyzer.model.StateCensusCodeCSV;
import com.bridgelabz.IndianCensusAnalyzer.model.StateCensusDAO;
import com.bridgelabz.IndianCensusAnalyzer.model.StatesCensusCSV;
import com.bridgelabz.IndianCensusAnalyzer.model.UsCensusCSV;
import com.google.gson.Gson;
import csvbuilder.CsvBuilderException;
import csvbuilder.CsvBuilderFactory;
import csvbuilder.IOpenCsvBuilder;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StateCensusAnalyzer {
    String JSONFILEPATH = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/main/resources/sortedJson.json";

    List<StateCensusDAO> censusList;

    public StateCensusAnalyzer() {
        this.censusList = new ArrayList();
    }

    /**
     * To Load The State Census Csv File
     *
     * @param StateCsvPath
     * @return
     * @throws CensusAnalyzerException
     */
    public int loadStateCensusData(String StateCsvPath) throws CensusAnalyzerException {
        return this.loadCensusData(StateCsvPath, StatesCensusCSV.class);
    }

    /**
     * To Load the StateCode CSV File
     *
     * @param StateCodeCsvPath
     * @return
     * @throws CensusAnalyzerException
     */
    public int loadStateCensusCodeData(String StateCodeCsvPath) throws CensusAnalyzerException {
        return this.loadCensusData(StateCodeCsvPath, StateCensusCodeCSV.class);
    }

    /**
     * To Load the StateCode CSV File
     *
     * @param StateCodeCsvPath
     * @return
     * @throws CensusAnalyzerException
     */
    public int loadUsCensusData(String StateCodeCsvPath) throws CensusAnalyzerException {
        return this.loadCensusData(StateCodeCsvPath, UsCensusCSV.class);

    }

    /**
     * This will load data for all format
     *
     * @param csvPath
     * @param censusCSVClass
     * @param <E>
     * @return
     * @throws CensusAnalyzerException
     */
    public <E> int loadCensusData(String csvPath, Class<E> censusCSVClass) throws CensusAnalyzerException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvPath));
            IOpenCsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<E> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, censusCSVClass);
            if (censusCSVClass.getName().contains("UsCensusCSV")) {
                while (censusCSVIterator.hasNext())
                    this.censusList.add(new StateCensusDAO((UsCensusCSV) censusCSVIterator.next()));
            } else if (censusCSVClass.getName().contains("StatesCensusCSV")) {
                while (censusCSVIterator.hasNext())
                    this.censusList.add(new StateCensusDAO((StatesCensusCSV) censusCSVIterator.next()));
            } else if (censusCSVClass.getName().contains("StateCensusCodeCSV")) {
                while (censusCSVIterator.hasNext())
                    this.censusList.add(new StateCensusDAO((StateCensusCodeCSV) censusCSVIterator.next()));
            }
            return censusList.size();
        } catch (FileNotFoundException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.WRONGEXTESNSION, "Please Enter Proper File Extension");
        } catch (InvalidPathException | IOException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEPATHNOTCORRECT, "Please Enter Proper File Path");
        } catch (RuntimeException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEINTERNALISSUE, "Please check in file Content ");
        } catch (CsvBuilderException e) {
            throw new CensusAnalyzerException(e.getMessage(), e.type.name());
        }
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
