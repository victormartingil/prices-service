package com.victormartingil.prices.infrastructure.web.exceptions.functional;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FunctionalErrorCode {

    PRICE_NOT_FOUND(1001, "Price not found"),
    DATE_FORMAT_INVALID(1002, "Invalid date format"),
    UNKNOWN_ERROR(1003, "Unknown error");

    private final int code;

    private final String detail;

}
