package io.github.tdminhnhat.module.service.impl;

import io.github.tdminhnhat.core.entity.BaseEntity;
import io.github.tdminhnhat.core.exception.DuplicateDataException;
import io.github.tdminhnhat.module.entity.Product;
import io.github.tdminhnhat.module.entity.ProductAttribute;
import io.github.tdminhnhat.module.entity.ProductSku;
import io.github.tdminhnhat.module.entity.ProductSkuAttribute;
import io.github.tdminhnhat.module.mapper.ProductCategoryMapper;
import io.github.tdminhnhat.module.mapper.ProductMapper;
import io.github.tdminhnhat.module.model.dto.ProductDto;
import io.github.tdminhnhat.module.model.vo.ProductVo;
import io.github.tdminhnhat.module.repository.*;
import io.github.tdminhnhat.module.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    // Repository
    AttributeRepository attributeRepository;

    ProductCategoryRepository productCategoryRepository;
    ProductRepository productRepository;
    ProductAttributeRepository productAttributeRepository;
    ProductSkuRepository productSkuRepository;
    ProductSkuAttributeRepository productSkuAttributeRepository;

    // Mapper
    ProductMapper productMapper;
    ProductCategoryMapper productCategoryMapper;

    // Service


    @Override
    @Transactional(rollbackOn = Exception.class)
    public ProductVo save(ProductDto request) throws Exception {
        if(productRepository.findByProductId(request.productId()).isPresent()) {
            throw new DuplicateDataException("productId has already existed");
        }

        // Save Product
        Product target = productMapper.toEntity(request);
        target.setProductCategory(productCategoryRepository.findById(request.productCategory()).orElseThrow(() -> new EntityNotFoundException("ProductCategory wasn't found")));
        Product product = productRepository.save(productMapper.toEntity(request));

        ProductVo productVo = productMapper.toVo(product);
        productVo.setProductCategory(productCategoryMapper.toVo(product.getProductCategory()));

        // Save ProductAttribute
        List<ProductVo.ProductAttributeVo> productAttributeVos = request.productAttributes().stream().map(item -> new ProductAttribute(
                    product,
                    attributeRepository.findById(item.getAttributeId()).orElseThrow(() -> new EntityNotFoundException("Attribute wasn't found")),
                    item.getValue()
            )).peek(productAttributeRepository::save).map(item -> new ProductVo.ProductAttributeVo(item.getAttribute().getId(), item.getAttribute().getAttributeName(), item.getValue())).toList();
        productVo.setProductAttributeVos(productAttributeVos);

        // Save ProductSku & ProductSkuAttribute
        if(!request.productSkus().isEmpty()) {
            if(!productSkuRepository.findBySkuCodeIn(request.productSkus().stream().map(ProductDto.ProductSkuDto::getSkuCode).toList()).isEmpty()) {
                throw new DuplicateDataException("Some skuCodes has already existed in database");
            }

            List<ProductVo.ProductSkuVo> productSkuVos = request.productSkus().stream().map(item -> {
                ProductSku productSku = productSkuRepository.save(new ProductSku(product, item.getSkuCode()));
                List<ProductVo.ProductAttributeVo> productSkuAttributeVo = item.getProductAttribute().stream().map(productAttribute -> new ProductSkuAttribute(
                        productSku,
                        attributeRepository.findById(productAttribute.getAttributeId()).orElseThrow(() -> new EntityNotFoundException("Attribute wasn't found")),
                        productAttribute.getValue()
                )).peek(productSkuAttributeRepository::save).map(productSkuAttribute -> new ProductVo.ProductAttributeVo(productSkuAttribute.getAttribute().getId(), productSkuAttribute.getAttribute().getAttributeName(), productSkuAttribute.getValue())).toList();

                return new ProductVo.ProductSkuVo(productSku.getSkuCode(), productSkuAttributeVo);
            }).toList();
            productVo.setProductSkuVos(productSkuVos);
        }

        return productVo;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ProductVo update(Long id, ProductDto request) throws Exception {
        return null;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ProductVo delete(Long id) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product wasn't found"));

        // Modifying Product
        product.setDeleted(true);
        Product productSaved = productRepository.save(product);

        return this.getVo(productSaved);
    }

    @Override
    public ProductVo findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product wasn't found"));
        return this.getVo(product);
    }

    @Override
    public List<ProductVo> findAll() {
        return List.of();
    }

    @Override
    public ProductVo getVo(BaseEntity entity) {
        ProductVo productVo = productMapper.toVo((Product) entity);
        return productVo;
    }

}
