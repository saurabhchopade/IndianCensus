package com.bridgelabz.IndianCensusAnalyzer.service;

import com.bridgelabz.IndianCensusAnalyzer.exception.CensusAnalyzerException;
import com.bridgelabz.IndianCensusAnalyzer.model.StateCensusCodeCSV;
import com.bridgelabz.IndianCensusAnalyzer.model.StateCensusDAO;

import java.util.List;

public class IndianStateCodeAdapter extends CensusAdapter {
    /**
     * Abstract Method
     *
     * @param StateCsvPath
     * @return
     * @throws CensusAnalyzerException
     */
    @Override
    public List<StateCensusDAO> loadCensusData(String StateCsvPath) throws CensusAnalyzerException {
        return super.loadCensusData(StateCensusCodeCSV.class, StateCsvPath);
    }
}
