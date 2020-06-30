package com.bridgelabz.IndianCensusAnalyzer.model;

import com.opencsv.bean.CsvBindByName;

public class StatesCensusCSV {

        @CsvBindByName(column = "State")
        private String State;

        @CsvBindByName(column = "Population")
        private String Population;

        @CsvBindByName(column = "AreaInSqKm")
        private String AreaInSqKm;

        @CsvBindByName(column = "DensityPerSqKm")
        private String DensityPerSqKm;
}

