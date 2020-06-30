package com.bridgelabz.IndianCensusAnalyzer.service;

import com.bridgelabz.IndianCensusAnalyzer.exception.CensusAnalyzerException;
import com.bridgelabz.IndianCensusAnalyzer.model.StateCensusCodeCSV;
import com.bridgelabz.IndianCensusAnalyzer.model.StatesCensusCSV;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class StateCensusAnalyzer {

    public int loadStateCensusData(String StateCsvPath) throws CensusAnalyzerException {
        int noOfEntries;
        try {
            try (Reader reader = Files.newBufferedReader(Paths.get(StateCsvPath))) {
                Iterator<StatesCensusCSV> censusCsvIterator = this.getCSVFileIterator(reader, StatesCensusCSV.class);
                Iterable<StatesCensusCSV> csvIterable = () -> censusCsvIterator;
                noOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            }
        } catch (FileNotFoundException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.WRONGEXTESNSION, "Please Enter Proper File Path");
        } catch (InvalidPathException | IOException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEPATHNOTCORRECT, "Please Enter Proper File Path");
        } catch (RuntimeException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEINTERNALISSUE, "Please check in file Content ");
        }
        return noOfEntries;
    }

    public int loadStateCensusCodeData(String StateCodeCsvPath) throws CensusAnalyzerException {
        int noOfEntries;
        try {
            try (Reader reader = Files.newBufferedReader(Paths.get(StateCodeCsvPath))) {
                Iterator<StateCensusCodeCSV> censusCsvIterator = this.getCSVFileIterator(reader, StateCensusCode.class);
                Iterable<StateCensusCodeCSV> csvIterable = () -> censusCsvIterator;
                noOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            }
        } catch (FileNotFoundException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.WRONGEXTESNSION, "Please Enter Proper File Extension");
        } catch (InvalidPathException | IOException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEPATHNOTCORRECT, "Please Enter Proper File Path");
        } catch (RuntimeException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEINTERNALISSUE, "Please check in file Content ");
        }
        return noOfEntries;
    }

    private <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CensusAnalyzerException {
        try {
            CsvToBean<E> csvToBean = new CsvToBeanBuilder(reader).withType(csvClass).
                    withIgnoreLeadingWhiteSpace(true).build();
            return csvToBean.iterator();
        } catch (IllegalStateException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEINTERNALISSUE, "Please Provide Correct Data");
        }
    }

}

