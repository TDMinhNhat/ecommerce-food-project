package io.github.tdminhnhat.module.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductCategoryDto(

        @NotBlank(message = "cannot be null or empty")
        @Size(min = 1, max = 100, message = "must be minimum 1 characters and maximum 100 characters")
        String categoryName,

        @Size(max = 255, message = "maximum length only 255 characters")
        String description,

        @Positive(message = "should be a number greater than 0")
        Long categoryParent
) {
}
