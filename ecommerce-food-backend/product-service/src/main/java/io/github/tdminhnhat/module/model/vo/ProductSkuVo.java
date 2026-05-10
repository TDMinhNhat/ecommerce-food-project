package io.github.tdminhnhat.module.model.vo;

import io.github.tdminhnhat.core.model.vo.BaseVo;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProductSkuVo extends BaseVo {

    Long productId;

    String skuCode;

    List<ProductAttributeVo> attributes;
}
