package com.bridgelabz.IndianCensusAnalyzer.model;

import com.opencsv.bean.CsvBindByName;

public class StatesCensusCSV {
        /**
         * Here We Match the Header value for further use
         */
        @CsvBindByName(column = "State",required = true)
        public String state;

        @CsvBindByName(column = "Population",required = true)
        public String population;

        @CsvBindByName(column = "AreaInSqKm",required = true)
        public String areaInSqKm;

        @CsvBindByName(column = "DensityPerSqKm",required = true)
        public String densityPerSqKm;

        @Override
        public String toString() {
                return "StatesCensusCSV {" +
                        "State='" + state + '\'' +
                        ", Population='" + population + '\'' +
                        ", AreaInSqKm='" + areaInSqKm + '\'' +
                        ", DensityPerSqKm='" + densityPerSqKm + '\'' +
                        '}';
        }

}

