package com.bridgelabz.IndianCensusAnalyzer.service;
import com.bridgelabz.IndianCensusAnalyzer.exception.CensusAnalyzerException;
import com.bridgelabz.IndianCensusAnalyzer.exception.CsvBuilderException;

import java.io.Reader;
import java.util.Iterator;

public interface OpenCsvBuilder {
     <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CsvBuilderException;
}
