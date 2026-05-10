package io.github.tdminhnhat.module.mapper;

import io.github.tdminhnhat.module.entity.Product;
import io.github.tdminhnhat.module.model.dto.ProductDto;
import io.github.tdminhnhat.module.model.vo.ProductVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mappings({
            @Mapping(target = "productCategory", ignore = true)
    })
    Product toEntity(ProductDto request);

    @Mappings({
            @Mapping(target = "productAttributeVos", ignore = true),
            @Mapping(target = "productSkuVos", ignore = true)
    })
    ProductVo toVo(Product entity);
}
