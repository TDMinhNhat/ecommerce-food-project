package io.github.tdminhnhat.module.service;

import io.github.tdminhnhat.core.service.IFileService;
import io.github.tdminhnhat.core.service.IService;
import io.github.tdminhnhat.module.model.dto.AddWithImageProductCategoryDto;
import io.github.tdminhnhat.module.model.dto.ProductCategoryDto;
import io.github.tdminhnhat.module.model.vo.ProductCategoryVo;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductCategoryService extends IService<ProductCategoryDto, Long>, IFileService<Long> {

    List<ProductCategoryVo> addList(List<ProductCategoryDto> request) throws Exception;

    ProductCategoryVo saveWithImage(ProductCategoryDto request, MultipartFile file) throws Exception;

    List<ProductCategoryVo> uploadOrUpdateFiles(List<Long> ids, List<MultipartFile> files) throws Exception;
}
