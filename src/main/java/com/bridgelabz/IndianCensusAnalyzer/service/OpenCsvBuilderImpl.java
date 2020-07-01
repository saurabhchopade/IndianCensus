package com.bridgelabz.IndianCensusAnalyzer.service;

import com.bridgelabz.IndianCensusAnalyzer.exception.CsvBuilderException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.Reader;
import java.util.Iterator;

public class OpenCsvBuilderImpl<E> implements OpenCsvBuilder {
    /**
     * Implementation of the interface Here
     * @param reader
     * @param csvClass
     * @return
     * @throws CsvBuilderException
     */
    public Iterator <E> getCSVFileIterator(Reader reader,Class csvClass) throws CsvBuilderException {
        try {
            CsvToBean<E> csvToBean = new CsvToBeanBuilder(reader).withType(csvClass).withIgnoreLeadingWhiteSpace(true).build();
            return csvToBean.iterator();
        } catch (IllegalStateException e) {
            throw new CsvBuilderException(e.getMessage(), CsvBuilderException.ExceptionType.PARSERERROR);
        }
    }
}

