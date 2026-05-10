package io.github.tdminhnhat.module.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

public record ProductDto(

        @NotBlank(message = "can not be null or empty")
        @Size(max = 50, message = "max length is {max} characters")
        String productId,

        @NotBlank(message = "can not be null or empty")
        @Size(max = 100, message = "max length is {max} characters")
        String productName,

        @NotBlank(message = "can not be null or empty")
        String productDescription,

        @NotNull(message = "can not be null or empty")
        Long productCategory,

        @NotNull(message = "can not be null or empty")
        @Size(min = 1, message = "minimum {min} attribute")
        List<ProductDto.ProductAttributeDto> productAttributes,

        List<ProductDto.ProductSkuDto> productSkus
) {

        @FieldDefaults(level = AccessLevel.PRIVATE)
        @Builder
        @Getter @Setter
        @NoArgsConstructor @AllArgsConstructor
        public static class ProductAttributeDto {
                @NotNull(message = "can not be null or empty")
                @Positive(message = "must be a number greater than 0")
                Long attributeId;

                @NotBlank(message = "can not be null or empty")
                String value;
        }

        @FieldDefaults(level = AccessLevel.PRIVATE)
        @Builder
        @Getter @Setter
        @NoArgsConstructor @AllArgsConstructor
        public static class ProductSkuDto {

                @NotBlank(message = "can not be null or empty")
                @Size(max = 50, message = "max length is {max} characters")
                String skuCode;

                List<ProductDto.ProductAttributeDto> productAttribute;
        }
}
