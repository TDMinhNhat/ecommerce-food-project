package io.github.tdminhnhat.module.controller;

import io.github.tdminhnhat.module.model.dto.AddWithImageProductCategoryDto;
import io.github.tdminhnhat.module.model.dto.ProductCategoryDto;
import io.github.tdminhnhat.module.model.vo.ProductCategoryVo;
import io.github.tdminhnhat.module.service.ProductCategoryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("${server.api.product-category}")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductCategoryController {

    ProductCategoryService productCategoryService;

    @PostMapping("/")
    public ResponseEntity<ProductCategoryVo> add(@Valid @RequestBody ProductCategoryDto request) throws Exception {
        return ResponseEntity.ok(productCategoryService.save(request));
    }

    @PostMapping(value = "/list")
    public ResponseEntity<List<ProductCategoryVo>> addList(@Valid @RequestBody List<ProductCategoryDto> request) throws Exception {
        return ResponseEntity.ok(productCategoryService.addList(request));
    }

    @PostMapping(value = "/add-with-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductCategoryVo> addWithImage(@Valid @ModelAttribute AddWithImageProductCategoryDto request) throws Exception {
        return ResponseEntity.ok(productCategoryService.saveWithImage(new ProductCategoryDto(request.categoryName(), request.description(), request.categoryParent(), request.note()), request.file()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCategoryVo> update(@PathVariable Long id, @Valid @RequestBody ProductCategoryDto request) throws Exception {
        return ResponseEntity.ok(productCategoryService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductCategoryVo> delete(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(productCategoryService.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryVo> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productCategoryService.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductCategoryVo>> findAll() {
        return ResponseEntity.ok(productCategoryService.findAll());
    }

    @PutMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductCategoryVo> uploadOrUpdateImage(@PathVariable Long id, @RequestPart MultipartFile file) {
        return ResponseEntity.ok(productCategoryService.uploadOrUpdateFile(file, id));
    }

    @PutMapping(value = "/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<ProductCategoryVo>> uploadOrUpdateImages(@RequestParam List<Long> ids, @RequestPart List<MultipartFile> files) throws Exception {
        return ResponseEntity.ok(productCategoryService.uploadOrUpdateFiles(ids, files));
    }

    @DeleteMapping("/{id}/image")
    public ResponseEntity<ProductCategoryVo> deleteImage(@PathVariable Long id) {
        return ResponseEntity.ok(productCategoryService.deleteFile(id));
    }
}
