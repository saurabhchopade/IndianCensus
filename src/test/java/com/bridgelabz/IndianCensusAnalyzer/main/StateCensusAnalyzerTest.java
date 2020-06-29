package com.bridgelabz.IndianCensusAnalyzer.main;

import org.junit.Test;

public class StateCensusAnalyzerTest {
    private static final String STATECENSUSCSVPATH ="d" ;


    @Test
    public void givenCsvFile_CheckNumberOfRecord_ShouldPass()
    {
        StateCensusAnalyzer stateCensus=new StateCensusAnalyzer();
        stateCensus.loadStateCensusData(STATECENSUSCSVPATH);
    }
}
