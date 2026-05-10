package io.github.tdminhnhat.module.model.dto;

import io.github.tdminhnhat.core.annotation.FileValidation;
import io.github.tdminhnhat.core.enums.FileTypeValidation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ProductMediaDto(

        @NotNull(message = "can not be null or empty")
        @Positive(message = "must be a number greater than 0")
        Long productId,

        @NotNull(message = "can not be null or empty")
        @FileValidation(type = FileTypeValidation.IMAGE, nullable = false, message = "")
        MultipartFile file
) {
}
