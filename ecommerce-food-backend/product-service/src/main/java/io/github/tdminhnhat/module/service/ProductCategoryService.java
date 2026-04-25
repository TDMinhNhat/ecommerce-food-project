package io.github.tdminhnhat.module.service;

import io.github.tdminhnhat.core.service.IFileService;
import io.github.tdminhnhat.core.service.IService;
import io.github.tdminhnhat.module.model.dto.ProductCategoryDto;

public interface ProductCategoryService extends IService<ProductCategoryDto, Long>, IFileService<Long> {

}
