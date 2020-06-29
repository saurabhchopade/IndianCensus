package com.bridgelabz.IndianCensusAnalyzer.controller;

import com.bridgelabz.IndianCensusAnalyzer.exception.CensusAnalyzerException;
import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyzerTest {
    private static final String STATECENSUSCSVPATH = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/test/resources/IndiaStateCensusData.csv";
    private static final String STATECODECSVPATH = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/test/resources/" +
            "IndiaStateCode.csv";
    private static final String WRONGPATH = "/home/saurabh/IdeaProjects/IndianateCensusData.csv";
    private static final String WRONGEXTESNSION = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/test/resources/IndiaStateCensusData";

    @Test
    public void givenStateCensusCsvFile_LoadData_ShouldReturnNoOfEntries() throws CensusAnalyzerException {
        StateCensusAnalyzer stateCensus = new StateCensusAnalyzer();
        int checkEntries = 0;
        checkEntries = stateCensus.loadStateCensusData(STATECENSUSCSVPATH);
        Assert.assertEquals(29, checkEntries);
    }

    @Test
    public void givenFilePath_NotCorrect_shouldHandleException() throws CensusAnalyzerException {
        StateCensusAnalyzer stateCensus = new StateCensusAnalyzer();
        int checkEntries = 0;
        checkEntries = stateCensus.loadStateCensusData(WRONGPATH);
        Assert.assertEquals(29, checkEntries);

    }

    @Test
    public void givenFilePath_FileExtensionNotCorrect_shouldHandleException() throws CensusAnalyzerException {
        StateCensusAnalyzer stateCensus = new StateCensusAnalyzer();
        int checkEntries = 0;
        checkEntries = stateCensus.loadStateCensusData(WRONGEXTESNSION);
        Assert.assertEquals(29, checkEntries);
    }

    @Test
    public void givenFile_DelimiterIncorrect_shouldHandleException() throws CensusAnalyzerException {
        StateCensusAnalyzer stateCensus = new StateCensusAnalyzer();
        int checkEntries = 0;
        checkEntries = stateCensus.loadStateCensusData(STATECENSUSCSVPATH);
        Assert.assertEquals(29, checkEntries);
    }

    @Test
    public void givenFile_HeaderIncorrect_shouldHandleException() throws CensusAnalyzerException {
        StateCensusAnalyzer stateCensus = new StateCensusAnalyzer();
        int checkEntries = 0;
        checkEntries = stateCensus.loadStateCensusData(STATECENSUSCSVPATH);
        Assert.assertEquals(29, checkEntries);
    }
}
