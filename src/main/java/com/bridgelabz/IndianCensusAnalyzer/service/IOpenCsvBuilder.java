package com.bridgelabz.IndianCensusAnalyzer.service;

import com.bridgelabz.IndianCensusAnalyzer.exception.CsvBuilderException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface IOpenCsvBuilder {
    <E> List<E> getCSVFileList(Reader reader, Class csvClass) throws CsvBuilderException;
    <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CsvBuilderException;
}
