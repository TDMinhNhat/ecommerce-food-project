package io.github.tdminhnhat.module.mapper;

import io.github.tdminhnhat.module.entity.ProductSku;
import io.github.tdminhnhat.module.model.vo.ProductSkuVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductSkuMapper {

    @Mappings({
            @Mapping(target = "productId", source = "product.id"),
            @Mapping(target = "attributes", ignore = true)
    })
    ProductSkuVo toVo(ProductSku entity);
}
