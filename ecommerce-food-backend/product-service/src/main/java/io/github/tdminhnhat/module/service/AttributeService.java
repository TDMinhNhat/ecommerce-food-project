package io.github.tdminhnhat.module.service;

import io.github.tdminhnhat.core.service.IService;
import io.github.tdminhnhat.module.model.dto.AttributeDto;
import io.github.tdminhnhat.module.model.vo.AttributeVo;

import java.util.List;

public interface AttributeService extends IService<AttributeDto, Long> {

    List<AttributeVo> saveList(List<AttributeDto> request);
}
