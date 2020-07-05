package com.bridgelabz.IndianCensusAnalyzer.model;

public class StateCensusDAO {
    public String State;
    public Integer population;
    public Integer areaInSqKm;
    public Integer densityPerSqKm;
    public String stateCode;
    public String usState;
    public Integer usPopulation;
    public Float totalArea;
    public Float usPopulationDensity;


    public <E> StateCensusDAO(UsCensusCSV usCensusCSV) {
        usState = usCensusCSV.state;
        usPopulation = usCensusCSV.population;
        totalArea = usCensusCSV.totalArea;
        usPopulationDensity = usCensusCSV.populationDensity;
    }

    public StateCensusDAO(StateCensusCodeCSV stateCensusCodeCSV) {
        stateCode = stateCensusCodeCSV.stateCode;
    }

    public StateCensusDAO(StatesCensusCSV statesCensusCSV) {
        State = statesCensusCSV.State;
        population = statesCensusCSV.population;
        areaInSqKm = statesCensusCSV.areaInSqKm;
        densityPerSqKm = statesCensusCSV.densityPerSqKm;
    }

    public Float getTotalArea() {
        return totalArea;
    }

    public Float getUsPopulationDensity() {
        return usPopulationDensity;
    }
    public Integer getUsPopulation()
    {
        return usPopulation;
    }
    public Integer getAreaInSqKm()
    {
        return areaInSqKm;
    }
    public Integer getDensityPerSqKm()

    {
        return densityPerSqKm;
    }
    public Integer getPopulation()
    {
        return population;
    }
    public  String getStateCode()
    {
        return stateCode;
    }
    public String getState()
    {
        return State;
    }
}
