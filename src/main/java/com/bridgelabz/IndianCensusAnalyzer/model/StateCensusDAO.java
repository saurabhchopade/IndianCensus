package com.bridgelabz.IndianCensusAnalyzer.model;

public class StateCensusDAO {
    public String state;
    public Integer population;
    public Integer areaInSqKm;
    public Integer densityPerSqKm;
    public String stateCode;
    public String stateId;
    public String usState;
    public Integer usPopulation;
    public Float housingUnits;
    public Float totalArea;
    public Float waterArea;
    public Float landArea;
    public Float usPopulationDensity;
    public Float housingDensity;

    public StateCensusDAO(StatesCensusCSV statesCensusCSV) {
        state = statesCensusCSV.state;
        population = statesCensusCSV.population;
        areaInSqKm = statesCensusCSV.areaInSqKm;
        densityPerSqKm = statesCensusCSV.densityPerSqKm;
    }

    public StateCensusDAO(StateCensusCodeCSV stateCensusCodeCSV) {
        stateCode = stateCensusCodeCSV.stateCode;
    }


    public StateCensusDAO(UsCensusCSV usCensusCSV) {
        stateId = usCensusCSV.stateId;
        usState = usCensusCSV.state;
        usPopulation = usCensusCSV.population;
        housingUnits = usCensusCSV.housingUnits;
        totalArea = usCensusCSV.totalArea;
        waterArea = usCensusCSV.waterArea;
        landArea = usCensusCSV.landArea;
        usPopulationDensity = usCensusCSV.populationDensity;
        housingDensity = usCensusCSV.housingDensity;
    }
}
