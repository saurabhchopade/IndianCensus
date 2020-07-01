package com.bridgelabz.IndianCensusAnalyzer.service;

import com.bridgelabz.IndianCensusAnalyzer.exception.CensusAnalyzerException;
import com.bridgelabz.IndianCensusAnalyzer.exception.CsvBuilderException;
import com.bridgelabz.IndianCensusAnalyzer.model.StateCensusCodeCSV;
import com.bridgelabz.IndianCensusAnalyzer.model.StatesCensusCSV;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class StateCensusAnalyzer {
    List<StatesCensusCSV> censusCSVList = null;
    List<StateCensusCodeCSV> censusCodeCSVList = null;

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
            censusCSVList = csvBuilder.getCSVFileList(reader, StatesCensusCSV.class);
            return censusCSVList.size();
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
            censusCodeCSVList = csvBuilder.getCSVFileList(reader, StateCensusCodeCSV.class);
            return censusCodeCSVList.size();

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

    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> csvIterable = () -> iterator;
        int noOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return noOfEntries;
    }

    /**
     * Sort As Per State
     * @return
     * @throws CensusAnalyzerException
     */
    public String getStateWiseSortedCensusData() throws CensusAnalyzerException {
        if (censusCSVList == null || censusCSVList.size() == 0) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.NOCENSUSDATA, "No Census Data");
        }
        Comparator<StatesCensusCSV> censusCSVComparator = Comparator.comparing(census -> census.state);
        this.sorting(censusCSVList, censusCSVComparator);
        String sortedStateCensusJson = new Gson().toJson(censusCSVList);
        return sortedStateCensusJson;
    }

    /**
     * Sort As per StateCode
     * @return
     * @throws CensusAnalyzerException
     */
    public String getStateCodeWiseSortedCensusData() throws CensusAnalyzerException {
        if (censusCodeCSVList == null || censusCodeCSVList.size() == 0) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.NOCENSUSDATA, "No Census Data");
        }
        Comparator<StateCensusCodeCSV> censusCSVComparator = Comparator.comparing(census -> census.stateCode);
        this.sorting(censusCodeCSVList, censusCSVComparator);
        String sortedStateCensusJson = new Gson().toJson(censusCodeCSVList);
        return sortedStateCensusJson;
    }

    /**
     * @param csvFileList
     * @param censusCSVComparator
     */
    private <E> void sorting(List<E> csvFileList, Comparator<E> censusCSVComparator) {
        for (int i = 0; i < csvFileList.size(); i++) {
            for (int j = 0; j < csvFileList.size() - i - 1; j++) {
                E census1 = csvFileList.get(j);
                E census2 = csvFileList.get(j + 1);
                if (censusCSVComparator.compare(census1, census2) > 0) {
                    csvFileList.set(j, census2);
                    csvFileList.set(j + 1, census1);
                }
            }
        }
    }
}

