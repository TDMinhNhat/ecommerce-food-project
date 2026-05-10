package io.github.tdminhnhat.core.exception.handle;

import io.github.tdminhnhat.core.exception.DuplicateDataException;
import io.github.tdminhnhat.core.exception.FileContentException;
import io.github.tdminhnhat.core.exception.FileValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerCustomExceptionHandle {

    @ExceptionHandler(FileContentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleFileContentException(FileContentException e) {
        return e.getMessage();
    }

    @ExceptionHandler(DuplicateDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDuplicateDataException(DuplicateDataException e) {
        return e.getMessage();
    }

    @ExceptionHandler(FileValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleFileValidationException(FileValidationException e) {
        return e.getMessage();
    }

}