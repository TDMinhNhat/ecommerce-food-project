package io.github.tdminhnhat.module.mapper;

import io.github.tdminhnhat.module.entity.Attribute;
import io.github.tdminhnhat.module.model.dto.AttributeDto;
import io.github.tdminhnhat.module.model.vo.AttributeVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AttributeMapper {

    Attribute toEntity(AttributeDto request);

    AttributeVo toVo(Attribute entity);
}
