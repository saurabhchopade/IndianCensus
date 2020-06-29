package com.bridgelabz.IndianCensusAnalyzer.util;

import com.opencsv.bean.CsvBindByName;

public class LoadStates {
    public class CSVUser {
        @CsvBindByName(column = "")
        private String name;

        @CsvBindByName(column = "email", required = true)
        private String email;

        @CsvBindByName(column = "phone")
        private String phoneNo;

        @CsvBindByName
        private String country;
}

