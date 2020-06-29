package com.bridgelabz.IndianCensusAnalyzer.exception;

public class CensusAnalyzerException extends Exception {
    /**
     * Enum used as Constant
     */
    public enum exeptiontype {
        FILEPATHNOTCORRECT, WRONGEXTESNSION, PARSINGERROR,FILEINTERNALISSUE;
    }

    public exeptiontype type;

    /**
     * @param type
     * @param message
     */
    public CensusAnalyzerException(exeptiontype type, String message) {
        super(message);
        this.type = type;
    }
}
