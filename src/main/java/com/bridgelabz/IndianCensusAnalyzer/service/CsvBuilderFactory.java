package com.bridgelabz.IndianCensusAnalyzer.service;

public class CsvBuilderFactory {
    public static OpenCsvBuilder createCsvBuilder() {
        return new OpenCsvBuilderImpl();
    }
}
