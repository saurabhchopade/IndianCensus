package com.bridgelabz.IndianCensusAnalyzer.model;

public class StateCensusDAO {
    public String state;
    public Integer population;
    public Integer areaInSqKm;
    public Integer densityPerSqKm;
    public String stateCode;

    public StateCensusDAO(StatesCensusCSV statesCensusCSV) {
        state = statesCensusCSV.state;
        population = statesCensusCSV.population;
        areaInSqKm = statesCensusCSV.areaInSqKm;
        densityPerSqKm = statesCensusCSV.densityPerSqKm;
    }

    public StateCensusDAO(StateCensusCodeCSV stateCensusCodeCSV) {
        stateCode = stateCensusCodeCSV.stateCode;
    }


}
