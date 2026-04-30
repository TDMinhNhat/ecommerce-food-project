package io.github.tdminhnhat.module.service;

import io.github.tdminhnhat.core.service.IService;
import io.github.tdminhnhat.module.entity.AttributeType;
import io.github.tdminhnhat.module.model.dto.AttributeTypeDto;
import jakarta.transaction.Transactional;

public interface AttributeTypeService extends IService<AttributeTypeDto, Long> {
    @Transactional(rollbackOn = Exception.class)
    AttributeType saveIfNotExist(AttributeTypeDto request);
}
