package io.github.tdminhnhat.module.service.impl;

import io.github.tdminhnhat.core.entity.BaseEntity;
import io.github.tdminhnhat.core.exception.FileException;
import io.github.tdminhnhat.core.util.FileActionUtil;
import io.github.tdminhnhat.module.entity.Product;
import io.github.tdminhnhat.module.entity.ProductMedia;
import io.github.tdminhnhat.module.mapper.ProductMediaMapper;
import io.github.tdminhnhat.module.model.dto.ProductMediaDto;
import io.github.tdminhnhat.module.model.vo.ProductMediaVo;
import io.github.tdminhnhat.module.repository.ProductMediaRepository;
import io.github.tdminhnhat.module.repository.ProductRepository;
import io.github.tdminhnhat.module.service.ProductMediaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductMediaServiceImpl implements ProductMediaService {

    // Repository
    ProductRepository productRepository;
    ProductMediaRepository productMediaRepository;

    // Mapper
    ProductMediaMapper productMediaMapper;

    // Utils
    FileActionUtil fileActionUtil;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ProductMediaVo save(ProductMediaDto request) throws Exception {
        if(request.file().getResource().getFilename() == null) throw new FileException(FileException.FileExceptionType.FILENAME_NOT_FOUND);
        Product product = productRepository.findById(request.productId()).orElseThrow(() -> new EntityNotFoundException("Product wasn't found"));

        String objectName = String.format("product_media/%d_%s_%s", product.getId(), product.getProductId(), UUID.fromString(request.file().getResource().getFilename()));
        ProductMedia productMedia = productMediaRepository.save(new ProductMedia(product, objectName));

        fileActionUtil.upload(request.file(), objectName);

        return this.getVo(productMedia);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    @Deprecated
    public ProductMediaVo update(Long id, ProductMediaDto request) throws Exception {
        return null;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ProductMediaVo delete(Long id) throws Exception {
        ProductMedia productMedia = productMediaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ProductMedia wasn't found"));
        productMedia.setDeleted(true);
        return this.getVo(productMediaRepository.save(productMedia));
    }

    @Override
    @Deprecated
    public ProductMediaVo findById(Long id) {
        return null;
    }

    @Override
    public List<ProductMediaVo> findAll() {
        return productMediaRepository.findAll().stream().map(productMedia -> {
            try {
                return this.getVo(productMedia);
            } catch (Exception e) {throw new RuntimeException(e.getMessage()); }
        }).toList();
    }

    @Override
    public ProductMediaVo getVo(BaseEntity entity) throws Exception {
        ProductMedia productMedia = (ProductMedia) entity;
        ProductMediaVo productMediaVo = productMediaMapper.toVo(productMedia);
        productMediaVo.setUrl(fileActionUtil.getUrlFile(productMedia.getMediaId()));
        return productMediaVo;
    }
}
