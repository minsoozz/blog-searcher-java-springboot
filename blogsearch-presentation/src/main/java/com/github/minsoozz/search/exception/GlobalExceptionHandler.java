package com.github.minsoozz.search.exception;

import com.github.minsoozz.search.exception.dto.ErrorResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BlogSearchException.class)
    ErrorResponseDto handleException(final BlogSearchException e) {
        logger.error(e.getMessage());
        return ErrorResponseDto.toErrorResponseDto(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FailedToAcquireLockException.class)
    ErrorResponseDto handleException(final FailedToAcquireLockException e) {
        logger.error(e.getMessage());
        return ErrorResponseDto.toErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    ErrorResponseDto handleException(final Exception e) {
        logger.error(e.getMessage());
        return ErrorResponseDto.toErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    ErrorResponseDto handleException(final Throwable e) {
        logger.error(e.getMessage());
        return ErrorResponseDto.toErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
