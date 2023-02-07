package com.prgrms.be.app.common.exception;

import com.prgrms.be.app.common.dto.ApiResponse;
import com.prgrms.be.app.common.dto.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler<T> {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ApiResponse<String> notFoundErrorHandler(EntityNotFoundException exception) {
        log.warn("EntityNotFoundException : ", exception);
        return ApiResponse.fail(HttpStatus.NOT_FOUND.value(), exception.getMessage(), ResponseMessage.ERRORED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ApiResponse<String> invalidParameterErrorHandler(IllegalArgumentException exception) {
        log.warn("IllegalArgumentException : ", exception);
        return ApiResponse.fail(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), ResponseMessage.ERRORED);
    }

    @ExceptionHandler(Exception.class)
    protected ApiResponse<String> internalServerErrorHandler(Exception exception) {
        log.error("IntervalServerError : ", exception);
        return ApiResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), ResponseMessage.ERRORED);
    }
}
