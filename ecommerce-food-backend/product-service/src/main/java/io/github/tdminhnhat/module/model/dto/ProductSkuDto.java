package io.github.tdminhnhat.module.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ProductSkuDto(

        @NotNull(message = "can not be null or empty")
        @Positive(message = "must be a number greater than 0")
        Long productId,

        @NotBlank(message = "can not be null or empty")
        @Size(max = 50, message = "max length is {max} characters")
        String skuCode,

        @NotNull(message = "can not be null or empty")
        @Size(min = 1, message = "min {min} attribute in this productSku")
        List<ProductAttributeDto> productAttributeDto
) {
}
