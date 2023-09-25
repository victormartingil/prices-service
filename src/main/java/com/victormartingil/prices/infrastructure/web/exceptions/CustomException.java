package com.victormartingil.prices.infrastructure.web.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomException extends RuntimeException {

    private final int code;

    private final String title;

    public CustomException(final String detail, final int code, final String title) {
        super(detail);
        this.code = code;
        this.title = title;
    }

}
