package io.github.tdminhnhat.module.model.dto;

import jakarta.validation.constraints.NotBlank;

public record AttributeTypeDto(

        @NotBlank(message = "can not be null or empty")
        String typeName
) {
}
