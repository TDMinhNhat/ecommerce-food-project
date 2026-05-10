package io.github.tdminhnhat.module.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductAttributeDto(

        @NotNull(message = "can not be null or empty")
        @Positive(message = "must be a number greater than 0")
        Long productId,

        @NotNull(message = "can not be null or empty")
        @Positive(message = "must be a number greater than 0")
        Long attributeId,

        @NotBlank(message = "can not be null or empty")
        String value
) {
}
