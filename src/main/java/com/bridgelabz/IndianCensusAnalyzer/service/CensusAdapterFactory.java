package com.bridgelabz.IndianCensusAnalyzer.service;

import com.bridgelabz.IndianCensusAnalyzer.exception.CensusAnalyzerException;
import com.bridgelabz.IndianCensusAnalyzer.model.StateCensusDAO;

import java.util.List;

public class CensusAdapterFactory {

    /**
     * It Call the as per Country
     *
     * @param country
     * @param csvPath
     * @return
     * @throws CensusAnalyzerException
     */
    public static List<StateCensusDAO> getCensusData(StateCensusAnalyzer.Country country, String csvPath) throws CensusAnalyzerException {
        if (country.equals(StateCensusAnalyzer.Country.INDIA))
            return new IndiaCensusAdapter().loadCensusData(csvPath);
        if (country.equals(StateCensusAnalyzer.Country.US))
            return new UsCensusAdapter().loadCensusData(csvPath);
        if (country.equals(StateCensusAnalyzer.Country.INDIACODE))
            return new IndianStateCodeAdapter().loadCensusData(csvPath);
        throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.INVALIDCOUNTRY, "Enter Valid Country");
    }
}
