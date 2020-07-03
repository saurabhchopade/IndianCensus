package com.bridgelabz.IndianCensusAnalyzer.service;

import com.bridgelabz.IndianCensusAnalyzer.exception.CensusAnalyzerException;
import com.bridgelabz.IndianCensusAnalyzer.exception.CsvBuilderException;
import com.bridgelabz.IndianCensusAnalyzer.model.StateCensusCodeCSV;
import com.bridgelabz.IndianCensusAnalyzer.model.StateCensusDAO;
import com.bridgelabz.IndianCensusAnalyzer.model.StatesCensusCSV;
import com.bridgelabz.IndianCensusAnalyzer.model.UsCensusCSV;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
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
        try {
            Reader reader = Files.newBufferedReader(Paths.get(StateCsvPath));
            IOpenCsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<StatesCensusCSV> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, StatesCensusCSV.class);
            while (censusCSVIterator.hasNext())
                this.censusList.add(new StateCensusDAO(censusCSVIterator.next()));
            return censusList.size();
        } catch (FileNotFoundException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.WRONGEXTESNSION, "Please Enter Proper File Path");
        } catch (RuntimeException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEINTERNALISSUE, "Please check in file Content ");
        } catch (IOException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEPATHNOTCORRECT, "Please Enter Proper File Path");
        } catch (CsvBuilderException e) {
            throw new CensusAnalyzerException(e.getMessage(), e.type.name());
        }
    }

    /**
     * To Load the StateCode CSV File
     * @param StateCodeCsvPath
     * @return
     * @throws CensusAnalyzerException
     */
    public int loadStateCensusCodeData(String StateCodeCsvPath) throws CensusAnalyzerException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(StateCodeCsvPath));
            IOpenCsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<StateCensusCodeCSV> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, StateCensusCodeCSV.class);
            new StateCensusAnalyzer();
            while (censusCSVIterator.hasNext())
                this.censusList.add(new StateCensusDAO(censusCSVIterator.next()));
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
     * To Load the StateCode CSV File
     * @param StateCodeCsvPath
     * @return
     * @throws CensusAnalyzerException
     */
    public int loadUsCensusData(String StateCodeCsvPath) throws CensusAnalyzerException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(StateCodeCsvPath));
            IOpenCsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<UsCensusCSV> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, UsCensusCSV.class);
            new StateCensusAnalyzer();
            while (censusCSVIterator.hasNext())
                this.censusList.add(new StateCensusDAO(censusCSVIterator.next()));
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
     * @return
     * @throws CensusAnalyzerException
     */
    public String getStateWiseSortedCensusData() {
        Comparator<StateCensusDAO> censusCSVComparator = Comparator.comparing(census -> census.state);
        this.sorting(censusList, censusCSVComparator);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    /**
     * Sort As per StateCode
     *
     * @return
     * @throws CensusAnalyzerException
     */
    public String getStateCodeWiseSortedCensusData() {
        Comparator<StateCensusDAO> censusCSVComparator = Comparator.comparing(census -> census.stateCode);
        this.sorting(censusList, censusCSVComparator);
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

    /**
     * Sorting method do sort
     *
     * @param csvFileList
     * @param censusCSVComparator
     */
    private <E> void sorting(List<E> csvFileList, Comparator<E> censusCSVComparator) {
        for (int move = 0; move < csvFileList.size(); move++) {
            for (int index = 0; index < csvFileList.size() - move - 1; index++) {
                E firstCensus = csvFileList.get(index);
                E secondCensus = csvFileList.get(index + 1);
                if (censusCSVComparator.compare(firstCensus, secondCensus) > 0) {
                    csvFileList.set(index, secondCensus);
                    csvFileList.set(index + 1, firstCensus);
                }
            }
        }
    }
}

