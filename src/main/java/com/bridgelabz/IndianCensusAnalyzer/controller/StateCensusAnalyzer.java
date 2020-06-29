package com.bridgelabz.IndianCensusAnalyzer.controller;

import com.bridgelabz.IndianCensusAnalyzer.model.LoadStates;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class StateCensusAnalyzer {
    /**
     * Loading data to count the number of entries
     * @param StateCsvPath
     * @return
     * @throws IOException
     */
    public int loadStateCensusData(String StateCsvPath) throws IOException {
        int noOfEntries;
        try (
                Reader reader = Files.newBufferedReader(Paths.get(StateCsvPath));
        ) {
            CsvToBean<LoadStates> csvToBean = new CsvToBeanBuilder(reader).withType(LoadStates.class).
                    withIgnoreLeadingWhiteSpace(true).build();
            Iterator<LoadStates> censusCsvIterator = csvToBean.iterator();
            Iterable<LoadStates> csvIterable = () -> censusCsvIterator;
            noOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        }
        return noOfEntries;
    }
}

