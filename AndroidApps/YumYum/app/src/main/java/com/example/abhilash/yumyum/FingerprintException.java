package com.example.abhilash.yumyum;

/**
 * Created by root on 7/2/18.
 */

public class FingerprintException extends Exception {

    public FingerprintException(String exceptionMessage){
        super(exceptionMessage);
    }

    public FingerprintException(Exception exception){
        super(exception);
    }
}
