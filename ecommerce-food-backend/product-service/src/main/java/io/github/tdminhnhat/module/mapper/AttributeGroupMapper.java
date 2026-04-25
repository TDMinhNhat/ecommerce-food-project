package io.github.tdminhnhat.module.mapper;

import io.github.tdminhnhat.module.entity.AttributeGroup;
import io.github.tdminhnhat.module.model.dto.AttributeGroupDto;
import io.github.tdminhnhat.module.model.vo.AttributeGroupVo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttributeGroupMapper {

    AttributeGroup toEntity(AttributeGroupDto request);

    AttributeGroupVo toVo(AttributeGroup entity);
}
