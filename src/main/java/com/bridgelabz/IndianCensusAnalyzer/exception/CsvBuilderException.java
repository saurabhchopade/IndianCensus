package com.bridgelabz.IndianCensusAnalyzer.exception;

public class CsvBuilderException extends Exception {

    public enum ExceptionType {
        PARSERERROR
    }

    public ExceptionType type;

    /**
     * This is constructor and Super call Parent  class constructor
     * @param message
     * @param type
     */
    public CsvBuilderException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
