package com.bridgelabz.IndianCensusAnalyzer.exception;

public class CensusAnalyzerException extends Exception{

    public enum exeptiontype {
        FILEPATHNOTCORRECT;
    }

    public  exeptiontype type;

    public CensusAnalyzerException(exeptiontype type, String message) {
        super(message);
        this.type = type;
    }
}
