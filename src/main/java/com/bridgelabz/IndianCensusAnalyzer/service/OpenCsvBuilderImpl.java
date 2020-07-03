package com.bridgelabz.IndianCensusAnalyzer.service;

import com.bridgelabz.IndianCensusAnalyzer.exception.CsvBuilderException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;
public class OpenCsvBuilderImpl<E> implements IOpenCsvBuilder {
    /**
     * Implementation of the interface Here
     *
     * @param reader
     * @param csvClass
     * @return
     * @throws CsvBuilderException
     */

    @Override
    public List getCSVFileList(Reader reader, Class csvClass) throws CsvBuilderException {
        return this.getCsvBean(reader, csvClass).parse();
    }

    /**
     * Implementation for get csv Bean
     *
     * @param reader
     * @param csvClass
     * @return
     * @throws CsvBuilderException
     */
    private CsvToBean<E> getCsvBean(Reader reader, Class csvClass) throws CsvBuilderException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            return csvToBeanBuilder.build();
        } catch (IllegalStateException e) {
            throw new CsvBuilderException(e.getMessage(), CsvBuilderException.ExceptionType.PARSERERROR);
        }
    }

    public Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CsvBuilderException {
        try {
            CsvToBean<E> csvToBean = new CsvToBeanBuilder(reader).withType(csvClass).withIgnoreLeadingWhiteSpace(true).build();
            return csvToBean.iterator();
        } catch (IllegalStateException e) {
            throw new CsvBuilderException(e.getMessage(), CsvBuilderException.ExceptionType.PARSERERROR);
        }
    }
}

