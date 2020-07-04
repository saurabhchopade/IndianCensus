package com.bridgelabz.IndianCensusAnalyzer.model;

import com.opencsv.bean.CsvBindByName;

public class UsCensusCSV {

    @CsvBindByName(column = "State Id", required = true)
    public static String stateId;

    @CsvBindByName(column = "State", required = true)
    public static String state;

    @CsvBindByName(column = "Population", required = true)
    public static Integer population;

    @CsvBindByName(column = "Housing units", required = true)
    public static Float housingUnits;

    @CsvBindByName(column = "Total area", required = true)
    public static Float totalArea;

    @CsvBindByName(column = "Water area", required = true)
    public static Float waterArea;

    @CsvBindByName(column = "Land area", required = true)
    public static Float landArea;

    @CsvBindByName(column = "Population Density", required = true)
    public static Float populationDensity;

    @CsvBindByName(column = "Housing Density", required = true)
    public static Float housingDensity;

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
