package io.github.tdminhnhat.module.model.qo;

import io.github.tdminhnhat.core.model.dto.Filter;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProductCategoryFilterQo extends Filter {

    String categoryName;

    String description;

    @Positive(message = "must be a number greater than 0")
    Long productCategoryParent;
}
