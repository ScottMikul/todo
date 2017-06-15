package com.teamtreehouse.techdegrees.exc;

/**
 * Created by scott on 6/14/2017.
 */
public class DaoException extends Exception {
    private final Exception originalException;

    public DaoException(Exception originalException, String message){
        super(message);
        this.originalException = originalException;
    }
}
