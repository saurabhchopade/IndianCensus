package com.bridgelabz.IndianCensusAnalyzer.service;

import com.bridgelabz.IndianCensusAnalyzer.exception.CensusAnalyzerException;
import com.bridgelabz.IndianCensusAnalyzer.model.StateCensusCodeCSV;
import com.bridgelabz.IndianCensusAnalyzer.model.StateCensusDAO;
import com.bridgelabz.IndianCensusAnalyzer.model.StatesCensusCSV;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyzerTest {
    private static final String STATECENSUSCSVPATH = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONGPATH = "/home/saurabh/IdeaProjects/IndianateCensusData.csv";
    private static final String WRONGEXTESNSION = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/test/resources/IndiaStateCensusData.pdf";
    private static final String WRONGSTATEDELIMITER = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/test/resources/IndiaStateCensusDataDelimiter.csv";
    private static final String WRONGSTATEHEADER = null;

    private static final String STATECODECSVPATH = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/main/resources/IndiaStateCode.csv";
    private static final String STATECODEDELIMITER = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/test/resources/IndiaStateCodeDelimiter.csv";
    private static final String STATECODEHEADER = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/test/resources/IndiaStateCodeHeade";
    private static final String USCENSUSDATA = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/test/resources/USCensusData.csv";
    StateCensusAnalyzer stateCensusAnalyzer;

    @Before
    public void init() {
        stateCensusAnalyzer = new StateCensusAnalyzer();
    }

    @Test
    public void givenStateCensusCsvFile_LoadData_ShouldMatchNoOFRecords() throws CensusAnalyzerException {
        int checkEntries;
        checkEntries = stateCensusAnalyzer.loadStateCensusData(StateCensusAnalyzer.Country.INDIA,STATECENSUSCSVPATH);
        Assert.assertEquals(29, checkEntries);
    }

    @Test
    public void givenFilePath_NotCorrect_shouldHandleException() {
        try {
            int checkEntries;
            checkEntries = stateCensusAnalyzer.loadStateCensusData(StateCensusAnalyzer.Country.INDIA,WRONGPATH);
            Assert.assertEquals(29, checkEntries);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.exeptiontype.WRONGEXTESNSION, e.type);
        }
    }

    @Test
    public void givenFilePath_FileExtensionNotCorrect_shouldHandleException() {
        try {
            int checkEntries;
            checkEntries = stateCensusAnalyzer.loadStateCensusData(StateCensusAnalyzer.Country.INDIA,WRONGEXTESNSION);
            Assert.assertEquals(29, checkEntries);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.exeptiontype.WRONGEXTESNSION, e.type);
        }
    }

    @Test
    public void givenFile_DelimiterIncorrect_shouldHandleException() {
        try {
            int checkEntries;
            checkEntries = stateCensusAnalyzer.loadStateCensusData(StateCensusAnalyzer.Country.INDIA,WRONGSTATEDELIMITER);
            Assert.assertEquals(29, checkEntries);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.exeptiontype.FILEINTERNALISSUE, e.type);
        }
    }

    @Test
    public void givenFile_HeaderIncorrect_shouldHandleException() {
        try {
            int checkEntries;
            checkEntries = stateCensusAnalyzer.loadStateCensusData(StateCensusAnalyzer.Country.INDIA,WRONGSTATEHEADER);
            Assert.assertEquals(29, checkEntries);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.exeptiontype.FILEINTERNALISSUE, e.type);
        }
    }

    @Test
    public void givenStateCodeFile__shouldMatchNumberOfRecords() {
        try {
            int checkStateCodeEntries;
            checkStateCodeEntries = stateCensusAnalyzer.loadStateCensusData(StateCensusAnalyzer.Country.INDIACODE,STATECODECSVPATH);
            Assert.assertEquals(37, checkStateCodeEntries);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.exeptiontype.FILEPATHNOTCORRECT, e.type);
        }
    }

    @Test
    public void givenStateCodeFilePath_InCorrect_shouldHandleException() {
        try {
            int checkStateCodeEntries;
            checkStateCodeEntries = stateCensusAnalyzer.loadStateCensusData(StateCensusAnalyzer.Country.INDIACODE,WRONGPATH);
            Assert.assertEquals(37, checkStateCodeEntries);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.exeptiontype.WRONGEXTESNSION, e.type);
        }
    }

    @Test
    public void givenStateCodeFilePath_FileExtensionNotCorrect_shouldHandleException() {
        try {
            int checkStateCodeEntries;
            checkStateCodeEntries = stateCensusAnalyzer.loadStateCensusData(StateCensusAnalyzer.Country.INDIACODE,WRONGEXTESNSION);
            Assert.assertEquals(37, checkStateCodeEntries);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.exeptiontype.WRONGEXTESNSION, e.type);
        }
    }

    @Test
    public void givenStateCodeFile_DelimiterIncorrect_shouldHandleException() {
        try {
            int checkStateCodeEntries;
            checkStateCodeEntries = stateCensusAnalyzer.loadStateCensusData(StateCensusAnalyzer.Country.INDIACODE,STATECODEDELIMITER);
            Assert.assertEquals(37, checkStateCodeEntries);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.exeptiontype.FILEINTERNALISSUE, e.type);
        }
    }

    @Test
    public void givenStateCodeFile_HeaderIncorrect_shouldHandleException() {
        try {
            int checkStateCodeEntries;
            checkStateCodeEntries = stateCensusAnalyzer.loadStateCensusData(StateCensusAnalyzer.Country.INDIACODE,STATECODEHEADER);
            Assert.assertEquals(37, checkStateCodeEntries);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.exeptiontype.WRONGEXTESNSION, e.type);
        }
    }

    //=======================================================================================================
    @Test
    public void givenStateCensusData_SortBasedOnState_ShouldReturnFirstStateSortedResult() throws CensusAnalyzerException{
        stateCensusAnalyzer.loadStateCensusData(StateCensusAnalyzer.Country.INDIA,STATECENSUSCSVPATH);
        String sortedCensusData = stateCensusAnalyzer.getStateWiseSortedCensusData();
        StatesCensusCSV[] statesCensusCSV = new Gson().fromJson(sortedCensusData, StatesCensusCSV[].class);
        Assert.assertEquals("Andhra Pradesh", statesCensusCSV[0].State);
    }
    @Test
    public void givenStateCensusData_SortBasedOnState_ShouldReturnFirstLastSortedResult() throws CensusAnalyzerException{
        stateCensusAnalyzer.loadStateCensusData(StateCensusAnalyzer.Country.INDIA,STATECENSUSCSVPATH);
        String sortedCensusData = stateCensusAnalyzer.getStateWiseSortedCensusData();
        StatesCensusCSV[] statesCensusCSV = new Gson().fromJson(sortedCensusData, StatesCensusCSV[].class);
        Assert.assertEquals("West Bengal", statesCensusCSV[28].State);
    }

    @Test
    public void givenStateCensusCodeData_SortBasedOnStateCode_ShouldReturnSortedResultAndFirstStateCode() throws CensusAnalyzerException {
        stateCensusAnalyzer.loadStateCensusData(StateCensusAnalyzer.Country.INDIACODE,STATECODECSVPATH);
        String sortedCensusData = stateCensusAnalyzer.getStateCodeWiseSortedCensusData();
        StateCensusCodeCSV[] statesCensusCSV = new Gson().fromJson(sortedCensusData, StateCensusCodeCSV[].class);
        Assert.assertEquals("AD", statesCensusCSV[0].stateCode);
    }

    @Test
    public void givenStateCensusCodeData_SortBasedOnStateCode_ShouldReturnSortedResultAndLastState() throws CensusAnalyzerException {
        stateCensusAnalyzer.loadStateCensusData(StateCensusAnalyzer.Country.INDIACODE,STATECODECSVPATH);
        String sortedCensusData = stateCensusAnalyzer.getStateCodeWiseSortedCensusData();
        StateCensusDAO[] statesCensusCSV = new Gson().fromJson(sortedCensusData, StateCensusDAO[].class);
        Assert.assertEquals("WB", statesCensusCSV[36].stateCode);
    }

    //====================================================================================================
    @Test
    public void givenStateCensusData_SortBasedOnPopulation_ShouldReturnSortedResultAndLowestPopulousState() throws CensusAnalyzerException, IOException {
        stateCensusAnalyzer.loadStateCensusData(StateCensusAnalyzer.Country.INDIA,STATECENSUSCSVPATH);
        int noOfStateSorted = stateCensusAnalyzer.sortByGivenData(StateCensusAnalyzer.SortBy.INDPOPULATION);
        Assert.assertEquals(29, noOfStateSorted);
    }

    @Test
    public void givenStateCensusData_SortBasedOnPopulationDensity_ShouldReturnSortedResultAndLowestPopulationDensityState() throws CensusAnalyzerException, IOException {
        stateCensusAnalyzer.loadStateCensusData(StateCensusAnalyzer.Country.INDIA,STATECENSUSCSVPATH);
        int noOfStateSorted = stateCensusAnalyzer.sortByGivenData(StateCensusAnalyzer.SortBy.INDDENSITY);
        Assert.assertEquals(29, noOfStateSorted);
    }

    @Test
    public void givenStateCensusData_SortBasedOnArea_ShouldReturnSortedResultState() throws CensusAnalyzerException, IOException {
        stateCensusAnalyzer.loadStateCensusData(StateCensusAnalyzer.Country.INDIA,STATECENSUSCSVPATH);
        int noOfStateSorted = stateCensusAnalyzer.sortByGivenData(StateCensusAnalyzer.SortBy.INDAREA);
        Assert.assertEquals(29, noOfStateSorted);
    }

    //==================US=============================
    @Test
    public void givenUsCensusData_SortBasedPopulation_ShouldReturnSortedResult() throws CensusAnalyzerException, IOException {
        stateCensusAnalyzer.loadStateCensusData(StateCensusAnalyzer.Country.US,USCENSUSDATA);
        int noOfStateSorted = stateCensusAnalyzer.sortByGivenData(StateCensusAnalyzer.SortBy.USPOPULOUSSTATE);
        Assert.assertEquals(51, noOfStateSorted);
    }

    @Test
    public void givenUsCensusData_SortBasedPopulationDensity_ShouldReturnSortedResult() throws CensusAnalyzerException, IOException {
        stateCensusAnalyzer.loadStateCensusData(StateCensusAnalyzer.Country.US,USCENSUSDATA);
        int noOfStateSorted = stateCensusAnalyzer.sortByGivenData(StateCensusAnalyzer.SortBy.USDENSITY);
        Assert.assertEquals(51, noOfStateSorted);
    }

    @Test
    public void givenUsCensusData_SortBasedTotalArea_ShouldReturnSortedResult() throws CensusAnalyzerException, IOException {
        stateCensusAnalyzer.loadStateCensusData(StateCensusAnalyzer.Country.US,USCENSUSDATA);
        int noOfStateSorted = stateCensusAnalyzer.sortByGivenData(StateCensusAnalyzer.SortBy.USTOTALAREA);
        Assert.assertEquals(51, noOfStateSorted);
    }

    @Test
    public void givenUsCensusData_GivePopulousState_ShouldReturnResult() throws CensusAnalyzerException {
        String populousState = stateCensusAnalyzer.givePopulousWithDensityState(USCENSUSDATA,STATECENSUSCSVPATH);
        Assert.assertEquals("District of Columbia", populousState);
    }
}
