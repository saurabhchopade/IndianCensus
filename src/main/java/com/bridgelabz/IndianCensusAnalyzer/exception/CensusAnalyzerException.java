package com.bridgelabz.IndianCensusAnalyzer.exception;

public class CensusAnalyzerException extends Exception {

    /**
     * Enum used as Constant
     */
    public enum exeptiontype {
        FILEPATHNOTCORRECT, WRONGEXTESNSION, PARSINGERROR, FILEINTERNALISSUE;
    }

    public exeptiontype type;

    /**
     * @param message
     * @param name
     */
    public CensusAnalyzerException(String message, String name) {
        super(message);
        this.type = exeptiontype.valueOf(name);
    }

    /**
     * This is constructor and Super call Parent  class constructor
     * @param type
     * @param message
     */
    public CensusAnalyzerException(String message, exeptiontype type) {
        super(message);
        this.type = type;
    }

    /**This is constructor and Super call Parent  class constructor
     * @param type
     * @param message
     */
    public CensusAnalyzerException(exeptiontype type, String message) {
        super(message);
        this.type = type;
    }
}
