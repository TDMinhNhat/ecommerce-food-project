package io.github.tdminhnhat.module.service.impl;

import io.github.tdminhnhat.core.entity.BaseEntity;
import io.github.tdminhnhat.core.model.vo.BaseVo;
import io.github.tdminhnhat.module.entity.Attribute;
import io.github.tdminhnhat.module.entity.AttributeGroup;
import io.github.tdminhnhat.module.entity.AttributeGroupItem;
import io.github.tdminhnhat.module.mapper.AttributeGroupMapper;
import io.github.tdminhnhat.module.mapper.AttributeMapper;
import io.github.tdminhnhat.module.model.dto.AttributeGroupItemDto;
import io.github.tdminhnhat.module.model.vo.AttributeGroupItemVo;
import io.github.tdminhnhat.module.model.vo.AttributeVo;
import io.github.tdminhnhat.module.repository.AttributeGroupItemRepository;
import io.github.tdminhnhat.module.repository.AttributeGroupRepository;
import io.github.tdminhnhat.module.repository.AttributeRepository;
import io.github.tdminhnhat.module.service.AttributeGroupItemService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AttributeGroupItemServiceImpl implements AttributeGroupItemService {

    // Repository
    AttributeGroupItemRepository attributeGroupItemRepository;
    AttributeRepository attributeRepository;
    AttributeGroupRepository attributeGroupRepository;

    // Mapper
    AttributeMapper attributeMapper;
    AttributeGroupMapper attributeGroupMapper;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public AttributeGroupItemVo save(AttributeGroupItemDto request) {
        Attribute attribute = attributeRepository.findById(request.attributeId()).orElseThrow(() -> new EntityNotFoundException("Attribute wasn't found"));
        AttributeGroup attributeGroup = attributeGroupRepository.findById(request.groupId()).orElseThrow(() -> new EntityNotFoundException("Attribute Group wasn't found"));

        AttributeGroupItem attributeGroupItem = new AttributeGroupItem(attribute, attributeGroup);
        return this.getVo(attributeGroupItemRepository.save(attributeGroupItem));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public AttributeGroupItemVo update(Long id, AttributeGroupItemDto request) {
        AttributeGroupItem attributeGroupItem = attributeGroupItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Attribute Group Item wasn't found"));
        attributeGroupItem.setAttribute(attributeRepository.findById(request.attributeId()).orElseThrow(() -> new EntityNotFoundException("Attribute Group wasn't found")));
        attributeGroupItem.setAttributeGroup(attributeGroupRepository.findById(request.groupId()).orElseThrow(() -> new EntityNotFoundException("Attribute Group wasn't found")));
        return this.getVo(attributeGroupItemRepository.save(attributeGroupItem));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public AttributeGroupItemVo delete(Long id) {
        AttributeGroupItem attributeGroupItem = attributeGroupItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Attribute Group Item wasn't found"));
        attributeGroupItem.setDeleted(true);
        return this.getVo(attributeGroupItemRepository.save(attributeGroupItem));
    }

    @Override
    public AttributeGroupItemVo findById(Long id) {
        AttributeGroupItem attributeGroupItem = attributeGroupItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Attribute Group Item wasn't found"));
        return this.getVo(attributeGroupItem);
    }

    @Override
    public List<AttributeGroupItemVo> findAll() {
        return attributeGroupItemRepository.findAll().stream().map(this::getVo).toList();
    }

    @Override
    public AttributeGroupItemVo getVo(BaseEntity entity) {
        AttributeGroupItem attributeGroupItem = (AttributeGroupItem) entity;
        AttributeGroupItemVo attributeGroupItemVo = new AttributeGroupItemVo();

        attributeGroupItemVo.setAttributeVo(attributeMapper.toVo(attributeGroupItem.getAttribute()));
        attributeGroupItemVo.setAttributeGroupVo(attributeGroupMapper.toVo(attributeGroupItem.getAttributeGroup()));
        return attributeGroupItemVo;
    }
}
