package io.github.tdminhnhat.core.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

public class FileException extends Exception {

    public FileException(FileExceptionType type) {
        super(type.message);
    }

    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    @Getter
    @AllArgsConstructor
    public enum FileExceptionType {

        NOT_FOUND("File wasn't found"),

        FILENAME_NOT_FOUND("Filename wasn't found");

        String message;
    }
}
