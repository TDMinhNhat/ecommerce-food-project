package io.github.tdminhnhat.module.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AttributeGroupDto(

        @NotBlank(message = "can not be null or empty")
        @Size(max = 150, message = "max length is {max} characters")
        String groupName,

        @NotBlank(message = "can not be null or empty")
        @Size(max = 100, message = "max length is {max} characters")
        String groupType,

        String note
) {
}
