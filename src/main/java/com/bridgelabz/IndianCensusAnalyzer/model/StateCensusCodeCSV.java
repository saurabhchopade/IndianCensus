package com.bridgelabz.IndianCensusAnalyzer.model;

import com.opencsv.bean.CsvBindByName;

public class StateCensusCodeCSV {
    /**
     * Here We Match the Header value of csv file for further use
     */
    @CsvBindByName(column = "SrNo", required = true)
    public static String srNo;

    @CsvBindByName(column = "State Name", required = true)
    public static String stateName;

    @CsvBindByName(column = "TIN", required = true)
    public static String tin;

    @CsvBindByName(column = "StateCode", required = true)
    public static String stateCode;
    @Override
    public String toString() {
        return "StatesCensusCodeCSV {" +
                "SrNo='" + srNo + '\'' +
                ", State Name='" + stateName + '\'' +
                ", TIN='" + tin + '\'' +
                ", StateCode='" + stateCode + '\'' +
                '}';
    }
}
