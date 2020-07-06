package com.bridgelabz.IndianCensusAnalyzer.service;

import com.bridgelabz.IndianCensusAnalyzer.exception.CensusAnalyzerException;
import com.bridgelabz.IndianCensusAnalyzer.model.StateCensusDAO;
import com.bridgelabz.IndianCensusAnalyzer.model.UsCensusCSV;

import java.util.List;

public class UsCensusAdapter extends CensusAdapter {
    /**
     * Abstract method load data
     *
     * @param StateCsvPath
     * @return
     * @throws CensusAnalyzerException
     */
    @Override
    public List<StateCensusDAO> loadCensusData(String StateCsvPath) throws CensusAnalyzerException {
        return super.loadCensusData(UsCensusCSV.class, StateCsvPath);
    }
}
