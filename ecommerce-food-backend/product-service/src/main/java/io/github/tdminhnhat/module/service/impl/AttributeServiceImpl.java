package io.github.tdminhnhat.module.service.impl;

import io.github.tdminhnhat.core.entity.BaseEntity;
import io.github.tdminhnhat.module.entity.Attribute;
import io.github.tdminhnhat.module.entity.AttributeType;
import io.github.tdminhnhat.module.mapper.AttributeMapper;
import io.github.tdminhnhat.module.model.dto.AttributeDto;
import io.github.tdminhnhat.module.model.vo.AttributeVo;
import io.github.tdminhnhat.module.repository.AttributeRepository;
import io.github.tdminhnhat.module.service.AttributeService;
import io.github.tdminhnhat.module.service.AttributeTypeService;
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

    // Service
    AttributeTypeService attributeTypeService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public AttributeVo save(AttributeDto request) {
        AttributeType attributeType = attributeTypeService.saveIfNotExist(request.attributeType());

        Attribute attribute = attributeMapper.toEntity(request);
        attribute.setAttributeType(attributeType);
        Attribute target = attributeRepository.save(attribute);

        return this.getVo(target);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public List<AttributeVo> saveList(List<AttributeDto> request) {
        return request.stream().map(this::save).toList();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public AttributeVo update(Long id, AttributeDto request) {
        AttributeType attributeType = attributeTypeService.saveIfNotExist(request.attributeType());

        Attribute attribute = attributeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Attribute wasn't found"));
        BeanUtils.copyProperties(attribute, request);
        attribute.setAttributeType(attributeType);
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
        try {
            Attribute attribute = (Attribute) entity;
            AttributeVo attributeVo = attributeMapper.toVo(attribute);
            attributeVo.setAttributeType(attributeTypeService.getVo(attribute.getAttributeType()));
            return attributeVo;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
