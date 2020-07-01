package com.bridgelabz.IndianCensusAnalyzer.model;

import com.opencsv.bean.CsvBindByName;

public class StateCensusCodeCSV {
    /**
     * Here We Match the Header value of csv file for further use
     */
    @CsvBindByName(column = "SrNo")
    private String SrNo;

    @CsvBindByName(column = "State Name")
    private String StateName;

    @CsvBindByName(column = "TIN")
    private String TIN;

    @CsvBindByName(column = "StateCode")
    private String StateCode;
}
