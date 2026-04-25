package io.github.tdminhnhat.module.mapper;

import io.github.tdminhnhat.module.entity.ProductCategory;
import io.github.tdminhnhat.module.model.dto.ProductCategoryDto;
import io.github.tdminhnhat.module.model.vo.ProductCategoryVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {

    ProductCategory toEntity(ProductCategoryDto request);

    @Mapping(target = "categoryParentId",
            expression = "java(entity.getProductCategoryParent() != null ? entity.getProductCategoryParent().getId() : null)")
    ProductCategoryVo toVo(ProductCategory entity);
}
