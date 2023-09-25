package com.victormartingil.prices.infrastructure.web.exceptions;

import com.victormartingil.prices.infrastructure.web.exceptions.functional.FunctionalErrorCode;
import com.victormartingil.prices.infrastructure.web.exceptions.functional.FunctionalException;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.ErrorCodeDto;
import org.openapitools.model.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.format.DateTimeParseException;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private HttpStatus getHttpStatus(final ErrorDto errorDto) {
        return switch (Objects.requireNonNull(errorDto.getCode())) {
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            case BAD_REQUEST, INVALID_ARGUMENT -> HttpStatus.BAD_REQUEST;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }

    private ErrorDto createErrorDto(final ErrorCodeDto code, final String errorDescription) {
        return new ErrorDto()
                .code(code)
                .message(errorDescription);
    }

    @ExceptionHandler(FunctionalException.class)
    public ResponseEntity<ErrorDto> handleFunctionalException(final FunctionalException fe) {
        final ErrorDto functionalErrorDto = this.buildFunctionalErrorDto(fe);

        log.error(functionalErrorDto.getMessage(), fe);
        return new ResponseEntity<>(functionalErrorDto, this.getHttpStatus(functionalErrorDto));
    }

    private ErrorDto buildFunctionalErrorDto(final FunctionalException te) {

        final ErrorCodeDto errorCode = switch (FunctionalErrorCode.valueOf(te.getTitle())) {
            case DATE_FORMAT_INVALID -> ErrorCodeDto.BAD_REQUEST;
            case PRICE_NOT_FOUND -> ErrorCodeDto.NOT_FOUND;
            default -> ErrorCodeDto.UNKNOWN_ERROR;
        };

        return this.createErrorDto(errorCode, te.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorDto> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException exception) {

        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(this.createErrorDto(ErrorCodeDto.BAD_REQUEST, exception.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException exception) {

        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(this.createErrorDto(ErrorCodeDto.BAD_REQUEST, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {

        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(this.createErrorDto(ErrorCodeDto.BAD_REQUEST, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorDto> handleDateTimeParseException(final DateTimeParseException exception) {

        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(this.createErrorDto(ErrorCodeDto.BAD_REQUEST, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(final Exception e) {
        log.error(e.getMessage(), e);
        final ErrorDto errorDto = createErrorDto(ErrorCodeDto.UNKNOWN_ERROR, e.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
