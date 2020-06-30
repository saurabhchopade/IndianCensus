package com.bridgelabz.IndianCensusAnalyzer.service;
import com.bridgelabz.IndianCensusAnalyzer.exception.CensusAnalyzerException;
import java.io.Reader;
import java.util.Iterator;

public interface OpenCsvBuilder {
     <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CensusAnalyzerException;
}
