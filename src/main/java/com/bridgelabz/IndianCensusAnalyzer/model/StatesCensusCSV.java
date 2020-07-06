package com.bridgelabz.IndianCensusAnalyzer.model;
import com.opencsv.bean.CsvBindByName;

public class StatesCensusCSV {
        /**
         * Here We Match the Header value for further use
         */
        @CsvBindByName(column = "State", required = true)
        public String State;

        @CsvBindByName(column = "Population", required = true)
        public Integer population;

        @CsvBindByName(column = "AreaInSqKm", required = true)
        public Integer areaInSqKm;

        @CsvBindByName(column = "DensityPerSqKm", required = true)
        public Float densityPerSqKm;

        @Override
        public String toString() {
                return "StatesCensusCSV {" +
                        "State='" + State + '\'' +
                        ", Population='" + population + '\'' +
                        ", AreaInSqKm='" + areaInSqKm + '\'' +
                        ", DensityPerSqKm='" + densityPerSqKm + '\'' +
                        '}';
        }
}

