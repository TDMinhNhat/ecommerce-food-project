package io.github.tdminhnhat.module.service.impl;

import io.github.tdminhnhat.core.entity.BaseEntity;
import io.github.tdminhnhat.module.entity.AttributeType;
import io.github.tdminhnhat.module.mapper.AttributeTypeMapper;
import io.github.tdminhnhat.module.model.dto.AttributeTypeDto;
import io.github.tdminhnhat.module.model.vo.AttributeTypeVo;
import io.github.tdminhnhat.module.repository.AttributeTypeRepository;
import io.github.tdminhnhat.module.service.AttributeTypeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AttributeTypeServiceImpl implements AttributeTypeService {

    // Repository
    AttributeTypeRepository attributeTypeRepository;

    // Mapper
    AttributeTypeMapper attributeTypeMapper;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public AttributeTypeVo save(AttributeTypeDto request) throws Exception {
        return this.getVo(attributeTypeRepository.save(attributeTypeMapper.toEntity(request)));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public AttributeType saveIfNotExist(AttributeTypeDto request) {
        Optional<AttributeType> attributeTypeOptional = attributeTypeRepository.findByTypeName(request.typeName());
        if(attributeTypeOptional.isPresent()) return attributeTypeOptional.get();
        else {
            AttributeType attributeType = attributeTypeMapper.toEntity(request);
            return attributeTypeRepository.save(attributeType);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public AttributeTypeVo update(Long id, AttributeTypeDto request) throws Exception {
        AttributeType attributeType = attributeTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AttributeType wasn't found"));
        BeanUtils.copyProperties(request, attributeType);
        return this.getVo(attributeTypeRepository.save(attributeTypeMapper.toEntity(request)));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public AttributeTypeVo delete(Long id) throws Exception {
        AttributeType attributeType = attributeTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AttributeType wasn't found"));
        attributeType.setDeleted(true);
        return this.getVo(attributeTypeRepository.save(attributeType));
    }

    @Override
    public AttributeTypeVo findById(Long id) {
        return this.getVo(attributeTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AttributeType wasn't found")));
    }

    @Override
    public List<AttributeTypeVo> findAll() {
        return attributeTypeRepository.findAll().stream().map(this::getVo).toList();
    }

    @Override
    public AttributeTypeVo getVo(BaseEntity entity) {
        AttributeType attributeType = (AttributeType) entity;
        return attributeTypeMapper.toVo(attributeType);
    }
}
