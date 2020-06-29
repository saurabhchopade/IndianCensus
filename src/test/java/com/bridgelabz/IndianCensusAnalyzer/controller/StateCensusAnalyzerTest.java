package com.bridgelabz.IndianCensusAnalyzer.controller;

import com.bridgelabz.IndianCensusAnalyzer.exception.CensusAnalyzerException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyzerTest {
    private static final String STATECENSUSCSVPATH = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/test/resources/IndiaStateCensusData.csv";
    private static final String STATECODECSVPATH = "/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/test/resources/" +
            "IndiaStateCode.csv";
    private static final String WRONGPATH = "/home/saurabh/IdeaProjects/IndianCzer/src/test/resources/IndiaStateCensusData.csv";

    @Test
    public void givenStateCensusCsvFile_LoadData_ShouldReturnNoOfEntries() throws CensusAnalyzerException {
        StateCensusAnalyzer stateCensus = new StateCensusAnalyzer();
        int checkEntries = 0;
        try {
            checkEntries = stateCensus.loadStateCensusData(STATECENSUSCSVPATH);
        } catch (IOException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEPATHNOTCORRECT, "Please Enter Proper File Path");
        }
        Assert.assertEquals(29, checkEntries);
    }

    @Test
    public void givenFilePath_NotCorrect_shouldHandleException() throws CensusAnalyzerException {
        StateCensusAnalyzer stateCensus = new StateCensusAnalyzer();
        int checkEntries = 0;
        try {
            checkEntries = stateCensus.loadStateCensusData(WRONGPATH);
        } catch (IOException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEPATHNOTCORRECT, "Please Enter Proper File Path");
        }
        Assert.assertEquals(29, checkEntries);
    }
}
