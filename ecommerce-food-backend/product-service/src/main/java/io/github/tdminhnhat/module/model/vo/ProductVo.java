package io.github.tdminhnhat.module.model.vo;

import io.github.tdminhnhat.core.entity.BaseEntity;
import io.github.tdminhnhat.core.model.vo.BaseVo;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProductVo extends BaseVo {

    String productId;

    String productName;

    String productDescription;

    ProductCategoryVo productCategory;

    List<ProductAttributeVo> productAttributeVos;

    List<ProductSkuVo> productSkuVos;

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Builder
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    public static class ProductAttributeVo {

        Long attributeId;

        String attributeName;

        String value;
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Builder
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    public static class ProductSkuVo {

        String skuCode;

        List<ProductVo.ProductAttributeVo> productAttributeVos;
    }

}
