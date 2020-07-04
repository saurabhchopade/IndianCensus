package com.bridgelabz.IndianCensusAnalyzer.model;

public class StateCensusDAO {
    public String State;
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

    /**
     * One DAO for all
     *
     * @param usCensusCSV
     * @param <E>
     */
    public <E> StateCensusDAO(E usCensusCSV) {
        stateId = UsCensusCSV.stateId;
        usState = UsCensusCSV.state;
        usPopulation = UsCensusCSV.population;
        housingUnits = UsCensusCSV.housingUnits;
        totalArea = UsCensusCSV.totalArea;
        waterArea = UsCensusCSV.waterArea;
        landArea = UsCensusCSV.landArea;
        usPopulationDensity = UsCensusCSV.populationDensity;
        housingDensity = UsCensusCSV.housingDensity;
        State = StatesCensusCSV.State;
        population = StatesCensusCSV.population;
        areaInSqKm = StatesCensusCSV.areaInSqKm;
        densityPerSqKm = StatesCensusCSV.densityPerSqKm;
        stateCode = StateCensusCodeCSV.stateCode;
    }
}
