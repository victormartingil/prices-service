package com.victormartingil.prices.infrastructure.web.exceptions;

import com.victormartingil.prices.infrastructure.web.exceptions.functional.FunctionalErrorCode;
import com.victormartingil.prices.infrastructure.web.exceptions.functional.FunctionalException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.ErrorCodeDto;
import org.openapitools.model.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler exceptionHandler;

    @Test
    void handleFunctionalExceptionDateFormatInvalidTest() {
        // Given
        final FunctionalException exception = new FunctionalException(FunctionalErrorCode.DATE_FORMAT_INVALID);

        // When
        final ResponseEntity<ErrorDto> responseEntity = this.exceptionHandler.handleFunctionalException(exception);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        final ErrorDto errorDTO = responseEntity.getBody();
        assertNotNull(errorDTO);
        assertEquals(ErrorCodeDto.BAD_REQUEST, errorDTO.getCode());
    }

    @Test
    void handleFunctionalExceptionPriceNotFoundTest() {
        // Given
        final FunctionalException exception = new FunctionalException(FunctionalErrorCode.PRICE_NOT_FOUND);

        // When
        final ResponseEntity<ErrorDto> responseEntity = this.exceptionHandler.handleFunctionalException(exception);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        final ErrorDto errorDTO = responseEntity.getBody();
        assertNotNull(errorDTO);
        assertEquals(ErrorCodeDto.NOT_FOUND, errorDTO.getCode());
    }

    @Test
    void handleFunctionalExceptionUnknownErrorTest() {
        // Given
        final FunctionalException exception = new FunctionalException(FunctionalErrorCode.UNKNOWN_ERROR);

        // When
        final ResponseEntity<ErrorDto> responseEntity = this.exceptionHandler.handleFunctionalException(exception);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        final ErrorDto errorDTO = responseEntity.getBody();
        assertNotNull(errorDTO);
        assertEquals(ErrorCodeDto.UNKNOWN_ERROR, errorDTO.getCode());
    }

    @Test
    void handleHttpRequestMethodNotSupportedExceptionTest() {
        // Given
        final HttpRequestMethodNotSupportedException exception = new HttpRequestMethodNotSupportedException("Method not supported");

        // When
        final ResponseEntity<ErrorDto> responseEntity = exceptionHandler.handleHttpRequestMethodNotSupportedException(exception);

        // Then
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, responseEntity.getStatusCode());
        final ErrorDto errorDto = responseEntity.getBody();
        assertNotNull(errorDto);
        assertEquals(ErrorCodeDto.BAD_REQUEST, errorDto.getCode());
    }

    @Test
    void handleMethodArgumentTypeMismatchExceptionTest() {
        // Given
        final MethodArgumentTypeMismatchException exception = Mockito.mock(MethodArgumentTypeMismatchException.class);

        // When
        final ResponseEntity<ErrorDto> responseEntity = exceptionHandler.handleMethodArgumentTypeMismatchException(exception);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        final ErrorDto errorDto = responseEntity.getBody();
        assertNotNull(errorDto);
        assertEquals(ErrorCodeDto.BAD_REQUEST, errorDto.getCode());
    }

    @Test
    void handleMethodArgumentNotValidExceptionTest() {
        // Given
        final MethodArgumentNotValidException exception = Mockito.mock(MethodArgumentNotValidException.class);

        // When
        final ResponseEntity<ErrorDto> responseEntity = exceptionHandler.handleMethodArgumentNotValidException(exception);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        final ErrorDto errorDto = responseEntity.getBody();
        assertNotNull(errorDto);
        assertEquals(ErrorCodeDto.BAD_REQUEST, errorDto.getCode());
    }

    @Test
    void handleDateTimeParseExceptionTest() {
        // Given
        final DateTimeParseException exception = Mockito.mock(DateTimeParseException.class);

        // When
        final ResponseEntity<ErrorDto> responseEntity = exceptionHandler.handleDateTimeParseException(exception);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        final ErrorDto errorDto = responseEntity.getBody();
        assertNotNull(errorDto);
        assertEquals(ErrorCodeDto.BAD_REQUEST, errorDto.getCode());
    }

    @Test
    void handleExceptionTest() {
        // Given
        final Exception exception = new Exception();

        // When
        final ResponseEntity<ErrorDto> responseEntity = exceptionHandler.handleException(exception);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        final ErrorDto errorDto = responseEntity.getBody();
        assertNotNull(errorDto);
        assertEquals(ErrorCodeDto.UNKNOWN_ERROR, errorDto.getCode());
    }
}