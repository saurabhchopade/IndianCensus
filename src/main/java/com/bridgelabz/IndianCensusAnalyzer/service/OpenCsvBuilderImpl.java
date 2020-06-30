package com.bridgelabz.IndianCensusAnalyzer.service;

import com.bridgelabz.IndianCensusAnalyzer.exception.CensusAnalyzerException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.Reader;
import java.util.Iterator;

public class OpenCsvBuilderImpl<E> implements OpenCsvBuilder {

    public Iterator <E> getCSVFileIterator(Reader reader,Class csvClass) throws CensusAnalyzerException {
        try {
            CsvToBean<E> csvToBean = new CsvToBeanBuilder(reader).withType(csvClass).withIgnoreLeadingWhiteSpace(true).build();
            return csvToBean.iterator();
        } catch (IllegalStateException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.exeptiontype.FILEINTERNALISSUE, "Please Provide Correct Data");
        }
    }
}

