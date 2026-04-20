package io.github.tdminhnhat.core.util;

import io.github.tdminhnhat.core.exception.FileContentException;
import org.springframework.web.multipart.MultipartFile;

public class FileValidationUtil {

    public static boolean validateImageFile(MultipartFile file) {
        String getContentType = file.getContentType();

        if(getContentType == null) {
            throw new FileContentException("File content wasn't found");
        }

        return getContentType.equalsIgnoreCase("image/png") ||
                getContentType.equalsIgnoreCase("image/jpeg");
    }
}
