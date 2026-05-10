package io.github.tdminhnhat.core.util;

import io.github.tdminhnhat.core.enums.FileTypeValidation;
import io.github.tdminhnhat.core.exception.FileContentException;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

public class FileValidationUtil {

    public static boolean validate(MultipartFile file, FileTypeValidation type) {
        switch (type) {
            case IMAGE -> validateImageFile(file);
            case VIDEO -> validateVideoFile(file);
            default -> { return true; }
        }
        return false;
    }

    public static boolean validateImageFile(MultipartFile file) {
        String getContentType = file.getContentType();

        if(getContentType == null) {
            throw new FileContentException("File content wasn't found");
        }

        return getContentType.equalsIgnoreCase("image/png") ||
                getContentType.equalsIgnoreCase("image/jpeg");
    }

    public static boolean validateVideoFile(MultipartFile file) {
        String getContentType = file.getContentType();

        if(getContentType == null) throw new FileContentException("File content wasn't found");

        return true;
    }
}
