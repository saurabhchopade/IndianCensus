package com.bridgelabz.IndianCensusAnalyzer.service;

import com.bridgelabz.IndianCensusAnalyzer.exception.CensusAnalyzerException;
import com.bridgelabz.IndianCensusAnalyzer.model.StateCensusDAO;
import com.bridgelabz.IndianCensusAnalyzer.model.StatesCensusCSV;

import java.util.List;

public class IndiaCensusAdapter extends CensusAdapter {

    /**
     * Constructor of class
     */
    @Override
    public List<StateCensusDAO> loadCensusData(String StateCsvPath) throws CensusAnalyzerException {
        return super.loadCensusData(StatesCensusCSV.class, StateCsvPath);
    }
}
