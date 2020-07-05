package com.bridgelabz.IndianCensusAnalyzer.service;

import com.bridgelabz.IndianCensusAnalyzer.exception.CensusAnalyzerException;
import com.bridgelabz.IndianCensusAnalyzer.model.StateCensusCodeCSV;
import com.bridgelabz.IndianCensusAnalyzer.model.StateCensusDAO;
import com.bridgelabz.IndianCensusAnalyzer.model.StatesCensusCSV;
import com.bridgelabz.IndianCensusAnalyzer.model.UsCensusCSV;
import csvbuilder.CsvBuilderException;
import csvbuilder.CsvBuilderFactory;
import csvbuilder.IOpenCsvBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CensusLoader {

    List<StateCensusDAO> censusList;

    public CensusLoader() {
        this.censusList = new ArrayList();
    }
    /**
     * This will load data for all format
     *
     * @param csvPath
     * @param censusCSVClass
     * @param <E>
     * @return
     * @throws CensusAnalyzerException
     */
    public <E> List loadCensusData(String csvPath, Class<E> censusCSVClass) throws CensusAnalyzerException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvPath));
            IOpenCsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<E> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, censusCSVClass);
            if (censusCSVClass.getName().contains("UsCensusCSV")) {
                while (censusCSVIterator.hasNext())
                    this.censusList.add(new StateCensusDAO((UsCensusCSV) censusCSVIterator.next()));
            } else if (censusCSVClass.getName().contains("StatesCensusCSV")) {
                while (censusCSVIterator.hasNext())
                    this.censusList.add(new StateCensusDAO((StatesCensusCSV) censusCSVIterator.next()));
            } else if (censusCSVClass.getName().contains("StateCensusCodeCSV")) {
                while (censusCSVIterator.hasNext())
                    this.censusList.add(new StateCensusDAO((StateCensusCodeCSV) censusCSVIterator.next()));
            }
            return censusList;
        } catch (FileNotFoundException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.WRONGEXTESNSION, "Please Enter Proper File Extension");
        } catch (InvalidPathException | IOException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEPATHNOTCORRECT, "Please Enter Proper File Path");
        } catch (RuntimeException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEINTERNALISSUE, "Please check in file Content ");
        } catch (CsvBuilderException e) {
            throw new CensusAnalyzerException(e.getMessage(), e.type.name());
        }
    }

}
