package ru.skillfactory.mybankservice.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.skillfactory.mybankservice.api.dto.BaseResponseDto;
import ru.skillfactory.mybankservice.exceptions.RecordNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public BaseResponseDto handleIllegalArgumentException(IllegalArgumentException ex) {
        return BaseResponseDto.asFailure(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotFoundException.class)
    public BaseResponseDto handleRecordNotFoundException(RecordNotFoundException ex) {
        return BaseResponseDto.asFailure(ex.getMessage());
    }
}