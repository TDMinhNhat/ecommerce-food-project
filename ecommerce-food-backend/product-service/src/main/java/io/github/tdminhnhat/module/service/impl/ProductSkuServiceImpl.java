package io.github.tdminhnhat.module.service.impl;

import io.github.tdminhnhat.core.entity.BaseEntity;
import io.github.tdminhnhat.module.entity.ProductSku;
import io.github.tdminhnhat.module.entity.ProductSkuAttribute;
import io.github.tdminhnhat.module.mapper.ProductSkuMapper;
import io.github.tdminhnhat.module.model.dto.ProductSkuDto;
import io.github.tdminhnhat.module.model.vo.ProductAttributeVo;
import io.github.tdminhnhat.module.model.vo.ProductSkuVo;
import io.github.tdminhnhat.module.repository.AttributeRepository;
import io.github.tdminhnhat.module.repository.ProductRepository;
import io.github.tdminhnhat.module.repository.ProductSkuAttributeRepository;
import io.github.tdminhnhat.module.repository.ProductSkuRepository;
import io.github.tdminhnhat.module.service.ProductSkuService;
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
public class ProductSkuServiceImpl implements ProductSkuService {

    // Repository
    AttributeRepository attributeRepository;
    ProductRepository productRepository;
    ProductSkuRepository productSkuRepository;
    ProductSkuAttributeRepository productSkuAttributeRepository;

    // Mapper
    ProductSkuMapper productSkuMapper;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ProductSkuVo save(ProductSkuDto request) throws Exception {
        // Saving ProductSku
        ProductSku productSku = new ProductSku(
                productRepository.findById(request.productId()).orElseThrow(() -> new EntityNotFoundException("Product wasn't found")),
                request.skuCode()
        );
        ProductSku productSkuSaved = productSkuRepository.save(productSku);

        // Saving ProductSkuAttribute
        List<ProductSkuAttribute> productSkuAttributes = request.productAttributeDto().stream().map(item -> new ProductSkuAttribute(productSkuSaved, attributeRepository.findById(item.attributeId()).orElseThrow(() -> new EntityNotFoundException("Attribute wasn't found")), item.value()))
                .toList();
        productSkuAttributeRepository.saveAll(productSkuAttributes);

        return this.getVo(productSkuSaved);
    }

    @Override
    public ProductSkuVo update(Long id, ProductSkuDto request) throws Exception {
        return null;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ProductSkuVo delete(Long id) throws Exception {
        ProductSku productSku = productSkuRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ProductSku wasn't found"));
        productSku.setDeleted(true);
        return this.getVo(productSkuRepository.save(productSku));
    }

    @Override
    public ProductSkuVo findById(Long id) {
        return null;
    }

    @Override
    public List<ProductSkuVo> findAll() {
        return List.of();
    }

    @Override
    public ProductSkuVo getVo(BaseEntity entity) {
        ProductSku productSku = (ProductSku) entity;
        ProductSkuVo productSkuVo = productSkuMapper.toVo(productSku);
        productSkuVo.setAttributes(productSku.getProductSkuAttributes().stream().map(item ->
            new ProductAttributeVo(
                    item.getProductSku().getId(),
                    new ProductAttributeVo.AttributeVo(
                            item.getAttribute().getId(),
                            item.getAttribute().getAttributeName(),
                            item.getAttribute().getAttributeType().getTypeName()),
                    item.getValue())
        ).toList());
        return productSkuVo;
    }
}
