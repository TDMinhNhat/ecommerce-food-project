package io.github.tdminhnhat.module.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AttributeGroupItemDto(

        @NotNull(message = "can not be null or empty")
        @Positive(message = "must be a number greater than 0")
        Long attributeId,

        @NotNull(message = "can not be null or empty")
        @Positive(message = "must be a number greater than 0")
        Long groupId,

        String note
) {
}
