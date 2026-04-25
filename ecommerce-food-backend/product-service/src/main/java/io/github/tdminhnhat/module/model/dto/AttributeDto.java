package io.github.tdminhnhat.module.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record AttributeDto(

        @NotBlank(message = "can not be null or empty")
        @Size(max = 50, message = "max length is {max} characters")
        String attributeName,

        String description,

        String note
) {
}
