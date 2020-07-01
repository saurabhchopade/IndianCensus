package com.bridgelabz.IndianCensusAnalyzer.service;

public class CsvBuilderFactory {
    public static IOpenCsvBuilder createCsvBuilder() {
        return new IOpenCsvBuilderImpl();
    }
}
