package com.bridgelabz.IndianCensusAnalyzer.controller;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyzerTest {
    private static final String STATECENSUSCSVPATH ="/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/test/resources/IndiaStateCensusData.csv";
    private static final String STATECODECSVPATH="/home/saurabh/IdeaProjects/IndianCensusAnalazer/src/test/resources/" +
            "IndiaStateCode.csv";
    @Test
    public void givenStateCensusCsvFile_LoadData_ShouldReturnNoOfEntries() throws IOException {
        StateCensusAnalyzer stateCensus=new StateCensusAnalyzer();
        int checkEntries=stateCensus.loadStateCensusData(STATECENSUSCSVPATH);
        Assert.assertEquals(29,checkEntries);
    }
}
