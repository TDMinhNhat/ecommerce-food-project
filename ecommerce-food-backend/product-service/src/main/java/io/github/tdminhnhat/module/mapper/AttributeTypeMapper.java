package io.github.tdminhnhat.module.mapper;

import io.github.tdminhnhat.module.entity.AttributeType;
import io.github.tdminhnhat.module.model.dto.AttributeTypeDto;
import io.github.tdminhnhat.module.model.vo.AttributeTypeVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AttributeTypeMapper {

    AttributeType toEntity(AttributeTypeDto request);

    AttributeTypeVo toVo(AttributeType entity);
}
