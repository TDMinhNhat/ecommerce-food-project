package io.github.tdminhnhat.module.service.impl;

import io.github.tdminhnhat.core.entity.BaseEntity;
import io.github.tdminhnhat.core.exception.DuplicateDataException;
import io.github.tdminhnhat.core.model.vo.BaseVo;
import io.github.tdminhnhat.module.entity.AttributeGroup;
import io.github.tdminhnhat.module.mapper.AttributeGroupMapper;
import io.github.tdminhnhat.module.model.dto.AttributeGroupDto;
import io.github.tdminhnhat.module.model.vo.AttributeGroupVo;
import io.github.tdminhnhat.module.model.vo.AttributeVo;
import io.github.tdminhnhat.module.repository.AttributeGroupRepository;
import io.github.tdminhnhat.module.service.AttributeGroupService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AttributeGroupServiceImpl implements AttributeGroupService {

    AttributeGroupRepository attributeGroupRepository;
    AttributeGroupMapper attributeGroupMapper;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public AttributeGroupVo save(AttributeGroupDto request) throws Exception {

        // Validate data
        if(this.validateDataExist(request).isPresent()) {
            throw new DuplicateDataException("Group Name or Group Type has existed");
        }

        AttributeGroup attributeGroup = attributeGroupMapper.toEntity(request);
        return this.getVo(attributeGroupRepository.save(attributeGroup));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public AttributeGroupVo update(Long id, AttributeGroupDto request) throws Exception {

        // Validate data
        AttributeGroup validateEntity = this.validateDataExist(request).orElse(new AttributeGroup());
        if (validateEntity.getId() != id) {
            throw new DuplicateDataException("Group Name or Group Type has existed");
        }

        AttributeGroup attributeGroup = attributeGroupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Attribute Group wasn't found"));
        BeanUtils.copyProperties(request, attributeGroup);
        return this.getVo(attributeGroupRepository.save(attributeGroup));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public AttributeGroupVo delete(Long id) {
        AttributeGroup attributeGroup = attributeGroupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Attribute Group wasn't found"));
        attributeGroup.setDeleted(true);
        return this.getVo(attributeGroupRepository.save(attributeGroup));
    }

    @Override
    public AttributeGroupVo findById(Long id) {
        AttributeGroup attributeGroup = attributeGroupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Attribute Group wasn't found"));
        return this.getVo(attributeGroup);
    }

    @Override
    public List<AttributeGroupVo> findAll() {
        return attributeGroupRepository.findAll().stream().map(this::getVo).toList();
    }

    @Override
    public AttributeGroupVo getVo(BaseEntity entity) {
        AttributeGroup attributeGroup = (AttributeGroup) entity;
        return attributeGroupMapper.toVo(attributeGroup);
    }

    public Optional<AttributeGroup> validateDataExist(AttributeGroupDto request) {
        return attributeGroupRepository.getAttributeGroupByNameOrType(request.groupName(), request.groupType());
    }
}
