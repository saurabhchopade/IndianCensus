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

public class CsvStateCensus {

    /**
     * Reading Csv File return NoOf Entries in csv
     *
     * @param StateCodeCsvPath
     * @return
     */
    public int loadStateCodeCensusData(String StateCodeCsvPath) {
        int noOfEntries = 0;
        try (
                Reader reader = Files.newBufferedReader(Paths.get(StateCodeCsvPath));
        ) {
            CsvToBean<LoadStates> csvToBean = new CsvToBeanBuilder(reader).withType(LoadStates.class).
                    withIgnoreLeadingWhiteSpace(true).build();
            Iterator<LoadStates> censusCsvIterator = csvToBean.iterator();
            Iterable<LoadStates> csvIterable = () -> censusCsvIterator;
            noOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        } catch (IOException e) {
        }
        return noOfEntries;

    }
}
