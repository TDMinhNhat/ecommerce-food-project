package io.github.tdminhnhat.module.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record AddWithImageProductCategoryDto(

        @NotBlank(message = "cannot be null or empty")
        @Size(min = 1, max = 100, message = "must be minimum 1 characters and maximum 100 characters")
        String categoryName,

        @Size(max = 255, message = "max length is {max} characters")
        String description,

        @Positive(message = "should be a number greater than 0")
        Long categoryParent,

        @Size(max = 255, message = "max length is {max} characters")
        String note,

        MultipartFile file
) {
}
