package io.github.tdminhnhat.core.exception.handle;

import io.github.tdminhnhat.core.exception.DuplicateDataException;
import io.github.tdminhnhat.core.exception.FileContentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerCustomExceptionHandle {

    @ExceptionHandler(FileContentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleFileContentException(FileContentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(DuplicateDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleDuplicateDataException(DuplicateDataException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
