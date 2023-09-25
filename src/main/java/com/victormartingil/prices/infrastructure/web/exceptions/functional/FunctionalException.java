package com.victormartingil.prices.infrastructure.web.exceptions.functional;


import com.victormartingil.prices.infrastructure.web.exceptions.CustomException;

public class FunctionalException extends CustomException {

    public FunctionalException(final FunctionalErrorCode functionalErrorCode) {
        super(functionalErrorCode.getDetail(), functionalErrorCode.getCode(), functionalErrorCode.name());
    }

}
