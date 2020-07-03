package com.bridgelabz.IndianCensusAnalyzer.model;

import com.opencsv.bean.CsvBindByName;

public class UsCensusCSV {

    @CsvBindByName(column = "State Id", required = true)
    public String stateId;

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public Integer population;

    @CsvBindByName(column = "Housing units", required = true)
    public Float housingUnits;

    @CsvBindByName(column = "Total area", required = true)
    public Float totalArea;

    @CsvBindByName(column = "Water area", required = true)
    public Float waterArea;

    @CsvBindByName(column = "Land area", required = true)
    public Float landArea;

    @CsvBindByName(column = "Population Density", required = true)
    public Float populationDensity;

    @CsvBindByName(column = "Housing Density", required = true)
    public Float housingDensity;

    @Override
    public String toString() {
        return "UsCensusCSV {" +
                " StateId='" + stateId + '\'' +
                ", State='" + state + '\'' +
                ", Population='" + population + '\'' +
                ", HousingUnits='" + housingUnits + '\'' +
                " TotalArea='" + totalArea + '\'' +
                ", WaterArea='" + waterArea + '\'' +
                ", LandArea ='" + landArea + '\'' +
                ", PopulationDensity='" + populationDensity + '\'' +
                ", HousingDensity ='" + housingDensity + '\'' +
                '}';
    }


}
