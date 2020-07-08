package com.bridgelabz.IndianCensusAnalyzer.model;

public class StateCensusDAO {
    public String State;
    public Integer population;
    public Integer areaInSqKm;
    public Float densityPerSqKm;
    public String stateCode;
    public String usState;
    public Integer usPopulation;
    public Float totalArea;
    public Float usPopulationDensity;

    /**
     * Us Census Data
     *
     * @param usCensusCSV
     */
    public StateCensusDAO(UsCensusCSV usCensusCSV) {
        usState = usCensusCSV.state;
        usPopulation = usCensusCSV.population;
        totalArea = usCensusCSV.totalArea;
        usPopulationDensity = usCensusCSV.populationDensity;
    }

    /**
     * IndiaStateCode Census Data
     *
     * @param stateCensusCodeCSV
     */
    public StateCensusDAO(StateCensusCodeCSV stateCensusCodeCSV) {
        stateCode = stateCensusCodeCSV.stateCode;
    }

    /**
     * India State Census Data
     *
     * @param statesCensusCSV
     */
    public StateCensusDAO(StatesCensusCSV statesCensusCSV) {
        State = statesCensusCSV.State;
        population = statesCensusCSV.population;
        areaInSqKm = statesCensusCSV.areaInSqKm;
        densityPerSqKm = statesCensusCSV.densityPerSqKm;
    }

    /**
     * All Getter methods for DAO
     *
     * @return
     */
    public Float getTotalArea() {
        return totalArea;
    }

    public Float getUsPopulationDensity() {
        return usPopulationDensity;
    }

    public Integer getUsPopulation() {
        return usPopulation;
    }

    public Integer getAreaInSqKm() {
        return areaInSqKm;
    }

    public Float getDensityPerSqKm() {
        return densityPerSqKm;
    }

    public Integer getPopulation() {
        return population;
    }

    public String getStateCode() {
        return stateCode;
    }

    public String getState() {
        return State;
    }
}
