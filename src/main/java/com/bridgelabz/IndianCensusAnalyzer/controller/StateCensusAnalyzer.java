package com.bridgelabz.IndianCensusAnalyzer.controller;
import com.bridgelabz.IndianCensusAnalyzer.exception.CensusAnalyzerException;
import com.bridgelabz.IndianCensusAnalyzer.model.LoadStates;
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
    /**
     * Loading data to count the number of entries
     *
     * @param StateCsvPath
     * @return
     * @throws IOException
     */
    public int loadStateCensusData(String StateCsvPath) throws CensusAnalyzerException {
        int noOfEntries;
        try {
            try (
                    Reader reader = Files.newBufferedReader(Paths.get(StateCsvPath));
            ) {
                CsvToBean<LoadStates> csvToBean = new CsvToBeanBuilder(reader).withType(LoadStates.class).
                        withIgnoreLeadingWhiteSpace(true).build();
                Iterator<LoadStates> censusCsvIterator = csvToBean.iterator();
                Iterable<LoadStates> csvIterable = () -> censusCsvIterator;
                noOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            }
        } catch (InvalidPathException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEPATHNOTCORRECT, "Please Enter Proper File Path");
        } catch (FileNotFoundException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.WRONGEXTESNSION, "Please Enter Proper File Path");
        } catch (RuntimeException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEINTERNALISSUE, "Please check in file Content ");
        } catch (IOException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEPATHNOTCORRECT, "Please Enter Proper File Path");
        }
        return noOfEntries;
    }
}

