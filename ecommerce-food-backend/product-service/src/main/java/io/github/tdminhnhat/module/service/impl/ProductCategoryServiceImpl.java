package io.github.tdminhnhat.module.service.impl;

import io.github.tdminhnhat.core.entity.BaseEntity;
import io.github.tdminhnhat.core.exception.FileContentException;
import io.github.tdminhnhat.core.util.FileActionUtil;
import io.github.tdminhnhat.core.util.FileValidationUtil;
import io.github.tdminhnhat.module.entity.ProductCategory;
import io.github.tdminhnhat.module.mapper.ProductCategoryMapper;
import io.github.tdminhnhat.module.model.dto.ProductCategoryDto;
import io.github.tdminhnhat.module.model.vo.ProductCategoryVo;
import io.github.tdminhnhat.module.repository.ProductCategoryRepository;
import io.github.tdminhnhat.module.service.ProductCategoryService;
import io.minio.errors.MinioException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    ProductCategoryRepository productCategoryRepository;
    ProductCategoryMapper productCategoryMapper;
    FileActionUtil fileActionUtil;

    @Override
    @Transactional(rollbackOn = RuntimeException.class)
    public ProductCategoryVo save(ProductCategoryDto request) {
        try {
            ProductCategory productCategory = productCategoryMapper.toEntity(request);

            if(request.categoryParent() != null) {
                productCategory.setProductCategoryParent(
                        productCategoryRepository
                                .findById(request.categoryParent())
                                .orElseThrow(() -> new EntityNotFoundException("Category parent wasn't found"))
                );
            }

            return getVo(productCategoryRepository.save(productCategory));
        } catch (MinioException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackOn = RuntimeException.class)
    public List<ProductCategoryVo> addList(List<ProductCategoryDto> request) throws Exception {
        return request.stream().map(item -> {
            try {
                return this.save(item);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }).toList();
    }

    @Override
    @Transactional(rollbackOn = RuntimeException.class)
    public ProductCategoryVo saveWithImage(ProductCategoryDto request, MultipartFile file) {
        try {
            ProductCategoryVo productCategoryVo = this.save(request);
            if(file != null && FileValidationUtil.validateImageFile(file)) {
                return this.uploadOrUpdateFile(file, productCategoryVo.getId());
            }
            return productCategoryVo;
        } catch (FileContentException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackOn = RuntimeException.class)
    public ProductCategoryVo update(Long id, ProductCategoryDto request) {
        try {
            ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category wasn't found"));
            BeanUtils.copyProperties(request, productCategory);

            if(request.categoryParent() != null) {
                productCategory.setProductCategoryParent(
                        productCategoryRepository
                                .findById(request.categoryParent())
                                .orElseThrow(() -> new EntityNotFoundException("Category parent wasn't found"))
                );
            }

            return getVo(productCategoryRepository.save(productCategory));
        } catch (MinioException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackOn = RuntimeException.class)
    public ProductCategoryVo delete(Long id) {
        try {
            ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category wasn't found"));
            productCategory.setDeleted(true);
            return getVo(productCategoryRepository.save(productCategory));
        } catch (MinioException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ProductCategoryVo findById(Long id) {
        try {
            return getVo(productCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category wasn't found")));
        } catch (MinioException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<ProductCategoryVo> findAll() {
        return productCategoryRepository.findAll().stream().map(productCategory -> {
            try {
                return this.getVo(productCategory);
            } catch (MinioException e) {
                throw new RuntimeException(e.getMessage());
            }
        }).toList();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ProductCategoryVo uploadOrUpdateFile(MultipartFile file, Long id) {
        try {
            if(FileValidationUtil.validateImageFile(file)) {
                ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("category wasn't found"));
                String objectName = productCategory.getId() + "_product_category_image";

                fileActionUtil.upload(file, objectName);
                productCategory.setImageId(objectName);

                return getVo(productCategoryRepository.save(productCategory));
            }
            throw new FileContentException("This is not file image");
        } catch (MinioException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public List<ProductCategoryVo> uploadOrUpdateFiles(List<Long> ids, List<MultipartFile> files) throws BadRequestException {
        if(ids.size() != files.size()) {
            throw new BadRequestException("The list id and file are not same size");
        }

        AtomicInteger index = new AtomicInteger(0);
        return ids.stream().map(id -> this.uploadOrUpdateFile(files.get(index.getAndIncrement()), id)).toList();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ProductCategoryVo deleteFile(Long id) {
        try {
            ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category wasn't found"));
            fileActionUtil.delete(productCategory.getImageId());
            productCategory.setImageId(null);
            return getVo(productCategoryRepository.save(productCategory));
        } catch (MinioException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ProductCategoryVo getUrlFile(Long id) {
        return null;
    }

    @Override
    public ProductCategoryVo getVo(BaseEntity entity) throws MinioException {
        ProductCategory productCategory = (ProductCategory) entity;
        ProductCategoryVo productCategoryVo = productCategoryMapper.toVo(productCategory);
        if(productCategory.getImageId() != null) {
            productCategoryVo.setImageUrl(fileActionUtil.getUrlFile(productCategory.getImageId()));
        }
        return productCategoryVo;
    }
}
