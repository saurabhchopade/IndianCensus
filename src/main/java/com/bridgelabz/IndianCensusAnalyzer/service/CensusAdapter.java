package com.bridgelabz.IndianCensusAnalyzer.service;

import com.bridgelabz.IndianCensusAnalyzer.exception.CensusAnalyzerException;
import com.bridgelabz.IndianCensusAnalyzer.model.StateCensusCodeCSV;
import com.bridgelabz.IndianCensusAnalyzer.model.StateCensusDAO;
import com.bridgelabz.IndianCensusAnalyzer.model.StatesCensusCSV;
import com.bridgelabz.IndianCensusAnalyzer.model.UsCensusCSV;
import csvbuilder.CsvBuilderException;
import csvbuilder.CsvBuilderFactory;
import csvbuilder.IOpenCsvBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class CensusAdapter {
    public List<StateCensusDAO> censusList = new ArrayList();

    /**
     * Abstract method
     *
     * @param StateCsvPath
     * @return
     * @throws CensusAnalyzerException
     */
    public abstract List<StateCensusDAO> loadCensusData(String StateCsvPath) throws CensusAnalyzerException;

    public <E> List<StateCensusDAO> loadCensusData(Class<E> censusCSVClass, String csvPath) throws CensusAnalyzerException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvPath));
            IOpenCsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<E> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, censusCSVClass);
            if (censusCSVClass.getName().equals("com.bridgelabz.IndianCensusAnalyzer.model.UsCensusCSV")) {
                while (censusCSVIterator.hasNext())
                    this.censusList.add(new StateCensusDAO((UsCensusCSV) censusCSVIterator.next()));
            } else if (censusCSVClass.getName().equals("com.bridgelabz.IndianCensusAnalyzer.model.StatesCensusCSV")) {
                while (censusCSVIterator.hasNext())
                    this.censusList.add(new StateCensusDAO((StatesCensusCSV) censusCSVIterator.next()));
            } else if (censusCSVClass.getName().equals("com.bridgelabz.IndianCensusAnalyzer.model.StateCensusCodeCSV")) {
                while (censusCSVIterator.hasNext())
                    this.censusList.add(new StateCensusDAO((StateCensusCodeCSV) censusCSVIterator.next()));
            } else
                throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEINTERNALISSUE, "Please check in file Content ");

            return censusList;

        } catch (
                RuntimeException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEINTERNALISSUE, "Please check in file Content ");
        } catch (
                NoSuchFileException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.WRONGEXTESNSION, "Please Enter Proper File Extension or file not found");
        } catch (
                CsvBuilderException e) {
            throw new CensusAnalyzerException(e.getMessage(), e.type.name());
        } catch (
                IOException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEPATHNOTCORRECT, "Please Enter Proper File Path");
        }
    }

}
