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

public class CensusLoader extends RuntimeException {

    List<StateCensusDAO> censusList;

    /**
     * Constructor of class
     */
    public CensusLoader() {
        this.censusList = new ArrayList();
    }

    /**
     * To select which we have to load on basis of user choice
     * @param country
     * @param StateCsvPath
     * @return
     * @throws CensusAnalyzerException
     */
    public List loadAllCensusData(StateCensusAnalyzer.Country country, String StateCsvPath) throws CensusAnalyzerException {
        if (country.equals(StateCensusAnalyzer.Country.INDIA)) {
            censusList = new CensusLoader().loadCensusData(country, StatesCensusCSV.class, StateCsvPath);
            return censusList;
        } else if (country.equals(StateCensusAnalyzer.Country.INDIACODE)) {
            censusList = new CensusLoader().loadCensusData(country, StateCensusCodeCSV.class, StateCsvPath);
            return censusList;
        } else if (country.equals(StateCensusAnalyzer.Country.US)) ;
        {
            censusList = new CensusLoader().loadCensusData(country, UsCensusCSV.class, StateCsvPath);
            return censusList;
        }
    }

    /**
     * This will load data for all format of data
     *
     * @param csvPath
     * @param censusCSVClass
     * @param <E>
     * @return
     * @throws CensusAnalyzerException
     */
    private <E> List loadCensusData(StateCensusAnalyzer.Country country, Class<E> censusCSVClass, String csvPath) throws CensusAnalyzerException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvPath));
            IOpenCsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<E> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, censusCSVClass);
            switch (country) {
                case US:
                    while (censusCSVIterator.hasNext())
                        this.censusList.add(new StateCensusDAO((UsCensusCSV) censusCSVIterator.next()));
                    break;
                case INDIA:
                    while (censusCSVIterator.hasNext())
                        this.censusList.add(new StateCensusDAO((StatesCensusCSV) censusCSVIterator.next()));
                    break;
                case INDIACODE:
                    while (censusCSVIterator.hasNext())
                        this.censusList.add(new StateCensusDAO((StateCensusCodeCSV) censusCSVIterator.next()));
                    break;
                default: throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEINTERNALISSUE, "Please check in file Content ");

            }
            return censusList;
        } catch (RuntimeException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEINTERNALISSUE, "Please check in file Content ");
        }
        catch (NoSuchFileException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.WRONGEXTESNSION, "Please Enter Proper File Extension or file not found");
        } catch (CsvBuilderException e) {
            throw new CensusAnalyzerException(e.getMessage(), e.type.name());
        }
        catch (IOException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEPATHNOTCORRECT, "Please Enter Proper File Path");
        }
    }

}
