package io.github.tdminhnhat.module.mapper;

import io.github.tdminhnhat.module.entity.ProductMedia;
import io.github.tdminhnhat.module.model.vo.ProductMediaVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductMediaMapper {

    @Mappings({
            @Mapping(target = "productId", source = "product.id")
    })
    ProductMediaVo toVo(ProductMedia entity);
}
