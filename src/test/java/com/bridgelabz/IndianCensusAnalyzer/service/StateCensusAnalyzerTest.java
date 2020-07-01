package com.bridgelabz.IndianCensusAnalyzer.service;
import com.bridgelabz.IndianCensusAnalyzer.exception.CensusAnalyzerException;
import com.bridgelabz.IndianCensusAnalyzer.model.StateCensusCodeCSV;
import com.bridgelabz.IndianCensusAnalyzer.model.StatesCensusCSV;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StateCensusAnalyzerTest {
    private static final String STATECENSUSCSVPATH = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONGPATH = "/home/saurabh/IdeaProjects/IndianateCensusData.csv";
    private static final String WRONGEXTESNSION = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/test/resources/IndiaStateCensusData";
    private static final String WRONGSTATEDELIMITER = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/test/resources/IndiaStateCensusDataDelimiter.csv";
    private static final String WRONGSTATEHEADER = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/test/resources/IndiaStateCensusDataDelimiter.csv";

    private static final String STATECODECSVPATH = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/main/resources/IndiaStateCode.csv";
    private static final String STATECODEDELIMITER = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/test/resources/IndiaStateCodeDelimiter.csv";
    private static final String STATECODEHEADER = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/test/resources/IndiaStateCodeHeader.csv";
    StateCensusAnalyzer stateCensusAnalyzer;

    @Before
    public void init() {
        stateCensusAnalyzer = new StateCensusAnalyzer();
    }

    @Test
    public void givenStateCensusCsvFile_LoadData_ShouldMatchNoOFRecords() throws CensusAnalyzerException {
        int checkEntries;
        checkEntries = stateCensusAnalyzer.loadStateCensusData(STATECENSUSCSVPATH);
        Assert.assertEquals(29, checkEntries);
    }

    @Test
    public void givenFilePath_NotCorrect_shouldHandleException() {
        try {
            int checkEntries;
            checkEntries = stateCensusAnalyzer.loadStateCensusData(WRONGPATH);
            Assert.assertEquals(29, checkEntries);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.exeptiontype.FILEPATHNOTCORRECT, e.type);
        }
    }

    @Test
    public void givenFilePath_FileExtensionNotCorrect_shouldHandleException() throws CensusAnalyzerException {
        int checkEntries;
        checkEntries = stateCensusAnalyzer.loadStateCensusData(WRONGEXTESNSION);
        Assert.assertEquals(29, checkEntries);
    }

    @Test
    public void givenFile_DelimiterIncorrect_shouldHandleException() throws CensusAnalyzerException {
        int checkEntries;
        checkEntries = stateCensusAnalyzer.loadStateCensusData(WRONGSTATEDELIMITER);
        Assert.assertEquals(29, checkEntries);
    }

    @Test
    public void givenFile_HeaderIncorrect_shouldHandleException() throws CensusAnalyzerException {
        int checkEntries;
        checkEntries = stateCensusAnalyzer.loadStateCensusData(WRONGSTATEHEADER);
        Assert.assertEquals(29, checkEntries);
    }

    //============================
    @Test
    public void givenStateCodeFile__shouldMatchNumberOfRecords() throws CensusAnalyzerException {
        int checkStateCodeEntries;
        checkStateCodeEntries = stateCensusAnalyzer.loadStateCensusCodeData(STATECODECSVPATH);
        Assert.assertEquals(37, checkStateCodeEntries);
    }

    @Test
    public void givenStateCodeFilePath_InCorrect_shouldHandleException() throws CensusAnalyzerException {
        int checkStateCodeEntries;
        checkStateCodeEntries = stateCensusAnalyzer.loadStateCensusCodeData(WRONGPATH);
        Assert.assertEquals(37, checkStateCodeEntries);
    }
    @Test
    public void givenStateCodeFilePath_FileExtensionNotCorrect_shouldHandleException() throws CensusAnalyzerException {
        int checkStateCodeEntries;
        checkStateCodeEntries = stateCensusAnalyzer.loadStateCensusCodeData(WRONGEXTESNSION);
        Assert.assertEquals(37, checkStateCodeEntries);
    }

    @Test
    public void givenStateCodeFile_DelimiterIncorrect_shouldHandleException() throws CensusAnalyzerException {
        int checkStateCodeEntries;
        checkStateCodeEntries = stateCensusAnalyzer.loadStateCensusCodeData(STATECODEDELIMITER);
        Assert.assertEquals(37, checkStateCodeEntries);
    }

    @Test
    public void givenStateCodeFile_HeaderIncorrect_shouldHandleException() throws CensusAnalyzerException {
        int checkStateCodeEntries;
        checkStateCodeEntries = stateCensusAnalyzer.loadStateCensusCodeData(STATECODEHEADER);
        Assert.assertEquals(37, checkStateCodeEntries);
    }

    //=====================================
    @Test
    public void givenStateCensusData_SortBasedOnState_ShouldReturnSortedResult() throws CensusAnalyzerException {
        stateCensusAnalyzer.loadStateCensusData(STATECENSUSCSVPATH);
        String sortedCensusData = stateCensusAnalyzer.getStateWiseSortedCensusData();
        StatesCensusCSV[] statesCensusCSV = new Gson().fromJson(sortedCensusData, StatesCensusCSV[].class);
        Assert.assertEquals("Andhra Pradesh", statesCensusCSV[0].state);
    }

    @Test
    public void givenStateCensusData_SortBasedOnState_ShouldReturnSortedResultAndCrossCheckLastState() throws CensusAnalyzerException {
        stateCensusAnalyzer.loadStateCensusData(STATECENSUSCSVPATH);
        String sortedCensusData = stateCensusAnalyzer.getStateWiseSortedCensusData();
        StatesCensusCSV[] statesCensusCSV = new Gson().fromJson(sortedCensusData, StatesCensusCSV[].class);
        Assert.assertEquals("West Bengal", statesCensusCSV[28].state);
    }

    @Test
    public void givenStateCensusCodeData_SortBasedOnStateCode_ShouldReturnSortedResultAndCrossCheckFirstStateCode() throws CensusAnalyzerException {
        stateCensusAnalyzer.loadStateCensusCodeData(STATECODECSVPATH);
        String sortedCensusData = stateCensusAnalyzer.getStateCodeWiseSortedCensusData();
        StateCensusCodeCSV[] statesCensusCSV = new Gson().fromJson(sortedCensusData, StateCensusCodeCSV[].class);
        Assert.assertEquals("AD", statesCensusCSV[0].stateCode);
    }

    @Test
    public void givenStateCensusCodeData_SortBasedOnStateCode_ShouldReturnSortedResultAndCrossCheckLastStateCode() throws CensusAnalyzerException {
        stateCensusAnalyzer.loadStateCensusCodeData(STATECODECSVPATH);
        String sortedCensusData = stateCensusAnalyzer.getStateCodeWiseSortedCensusData();
        StateCensusCodeCSV[] statesCensusCSV = new Gson().fromJson(sortedCensusData, StateCensusCodeCSV[].class);
        Assert.assertEquals("WB", statesCensusCSV[36].stateCode);
    }

}
