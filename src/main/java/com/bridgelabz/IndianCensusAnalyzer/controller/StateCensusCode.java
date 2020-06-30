package com.bridgelabz.IndianCensusAnalyzer.controller;

import com.bridgelabz.IndianCensusAnalyzer.exception.CensusAnalyzerException;
import com.bridgelabz.IndianCensusAnalyzer.model.LoadStateCode;
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

public class StateCensusCode {

    /**
     * Reading Csv File return NoOf Entries in csv
     *
     * @param StateCodeCsvPath
     * @return
     */
    public int loadStateCensusCodeData(String StateCodeCsvPath) throws CensusAnalyzerException {
        int noOfEntries = 0;
        try {
            try (
                    Reader reader = Files.newBufferedReader(Paths.get(StateCodeCsvPath));
            ) {
                CsvToBean<LoadStateCode> csvToBean = new CsvToBeanBuilder(reader).withType(LoadStateCode.class).
                        withIgnoreLeadingWhiteSpace(true).build();
                Iterator<LoadStateCode> censusCsvIterator = csvToBean.iterator();
                Iterable<LoadStateCode> csvIterable = () -> censusCsvIterator;
                noOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            }
        } catch (FileNotFoundException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.WRONGEXTESNSION, "Please Enter Proper File Extension");
        } catch (InvalidPathException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEPATHNOTCORRECT, "Please Enter Proper File Path");
        } catch (RuntimeException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEINTERNALISSUE, "Please check in file Content ");
        } catch (IOException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEPATHNOTCORRECT, "Please Enter Proper File Path");
        }
        return noOfEntries;
    }
}
