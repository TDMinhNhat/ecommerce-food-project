package io.github.tdminhnhat.module.service.impl;

import io.github.tdminhnhat.core.entity.BaseEntity;
import io.github.tdminhnhat.module.entity.Attribute;
import io.github.tdminhnhat.module.mapper.AttributeMapper;
import io.github.tdminhnhat.module.model.dto.AttributeDto;
import io.github.tdminhnhat.module.model.dto.AttributeGroupItemDto;
import io.github.tdminhnhat.module.model.vo.AttributeGroupItemVo;
import io.github.tdminhnhat.module.model.vo.AttributeVo;
import io.github.tdminhnhat.module.repository.AttributeRepository;
import io.github.tdminhnhat.module.service.AttributeGroupItemService;
import io.github.tdminhnhat.module.service.AttributeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AttributeServiceImpl implements AttributeService {

    // Repository
    AttributeRepository attributeRepository;

    // Mapper
    AttributeMapper attributeMapper;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public AttributeVo save(AttributeDto request) {
        Attribute attribute = attributeMapper.toEntity(request);
        Attribute target = attributeRepository.save(attribute);
        return this.getVo(target);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public AttributeVo update(Long id, AttributeDto request) {
        Attribute attribute = attributeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Attribute wasn't found"));
        BeanUtils.copyProperties(attribute, request);
        return this.getVo(attributeRepository.save(attribute));
    }

    @Override
    public AttributeVo delete(Long id) {
        Attribute attribute = attributeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Attribute wasn't found"));
        attribute.setDeleted(true);
        return this.getVo(attributeRepository.save(attribute));
    }

    @Override
    public AttributeVo findById(Long id) {
        Attribute attribute = attributeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Attribute wasn't found"));
        return this.getVo(attribute);
    }

    @Override
    public List<AttributeVo> findAll() {
        return attributeRepository.findAll().stream().map(this::getVo).toList();
    }

    @Override
    public AttributeVo getVo(BaseEntity entity) {
        Attribute attribute = (Attribute) entity;
        return attributeMapper.toVo(attribute);
    }
}
