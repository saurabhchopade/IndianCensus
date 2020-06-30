package com.bridgelabz.IndianCensusAnalyzer.service;
import com.bridgelabz.IndianCensusAnalyzer.exception.CensusAnalyzerException;
import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyzerTest {
    private static final String STATECENSUSCSVPATH = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONGPATH = "/home/saurabh/IdeaProjects/IndianateCensusData.csv";
    private static final String WRONGEXTESNSION = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/test/resources/IndiaStateCensusData";
    private static final String STATECODECSVPATH = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/test/resources/IndiaStateCode.csv";
    @Test
    public void givenStateCensusCsvFile_LoadData_ShouldMatchNoOFRecords() throws CensusAnalyzerException {
        StateCensusAnalyzer stateCensus = new StateCensusAnalyzer();
        int checkEntries;
        checkEntries = stateCensus.loadStateCensusData(STATECENSUSCSVPATH);
        Assert.assertEquals(29, checkEntries);
    }

    @Test
    public void givenFilePath_NotCorrect_shouldHandleException() throws CensusAnalyzerException {
        StateCensusAnalyzer stateCensus = new StateCensusAnalyzer();
        int checkEntries;
        checkEntries = stateCensus.loadStateCensusData(WRONGPATH);
        Assert.assertEquals(29, checkEntries);
    }

    @Test
    public void givenFilePath_FileExtensionNotCorrect_shouldHandleException() throws CensusAnalyzerException {
        StateCensusAnalyzer stateCensus = new StateCensusAnalyzer();
        int checkEntries;
        checkEntries = stateCensus.loadStateCensusData(WRONGEXTESNSION);
        Assert.assertEquals(29, checkEntries);
    }

    @Test
    public void givenFile_DelimiterIncorrect_shouldHandleException() throws CensusAnalyzerException {
        StateCensusAnalyzer stateCensus = new StateCensusAnalyzer();
        int checkEntries;
        checkEntries = stateCensus.loadStateCensusData(STATECENSUSCSVPATH);
        Assert.assertEquals(29, checkEntries);
    }

    @Test
    public void givenFile_HeaderIncorrect_shouldHandleException() throws CensusAnalyzerException {
        StateCensusAnalyzer stateCensus = new StateCensusAnalyzer();
        int checkEntries;
        checkEntries = stateCensus.loadStateCensusData(STATECENSUSCSVPATH);
        Assert.assertEquals(29, checkEntries);
    }
//============================
    @Test
    public void givenStateCodeFile__shouldMatchNumberOfRecords() throws CensusAnalyzerException {

        StateCensusCode stateCensusCode = new StateCensusCode();
        int checkStateCodeEntries;
        checkStateCodeEntries=stateCensusCode.loadStateCensusCodeData(STATECODECSVPATH);
        Assert.assertEquals(37,checkStateCodeEntries);
    }
    @Test
    public void givenStateCodeFilePath_InCorrect_shouldHandleException() throws CensusAnalyzerException {

        StateCensusCode stateCensusCode = new StateCensusCode();
        int checkStateCodeEntries;
        checkStateCodeEntries = stateCensusCode.loadStateCensusCodeData(WRONGPATH);
        Assert.assertEquals(37, checkStateCodeEntries);
    }
    @Test
    public void givenStateCodeFilePath_FileExtensionNotCorrect_shouldHandleException() throws CensusAnalyzerException {
        StateCensusCode stateCensusCode = new StateCensusCode();
        int checkStateCodeEntries;
        checkStateCodeEntries = stateCensusCode.loadStateCensusCodeData(WRONGEXTESNSION);
        Assert.assertEquals(37, checkStateCodeEntries);
    }

    @Test
    public void givenStateCodeFile_DelimiterIncorrect_shouldHandleException() throws CensusAnalyzerException {
        StateCensusCode stateCensusCode = new StateCensusCode();
        int checkStateCodeEntries;
        checkStateCodeEntries = stateCensusCode.loadStateCensusCodeData(STATECODECSVPATH);
        Assert.assertEquals(37, checkStateCodeEntries);
    }

    @Test
    public void givenStateCodeFile_HeaderIncorrect_shouldHandleException() throws CensusAnalyzerException {
        StateCensusCode stateCensusCode = new StateCensusCode();
        int checkStateCodeEntries;
        checkStateCodeEntries = stateCensusCode.loadStateCensusCodeData(STATECODECSVPATH);
        Assert.assertEquals(37, checkStateCodeEntries);
    }
}
