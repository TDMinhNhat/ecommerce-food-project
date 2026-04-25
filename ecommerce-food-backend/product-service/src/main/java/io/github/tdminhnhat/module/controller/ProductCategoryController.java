package io.github.tdminhnhat.module.controller;

import io.github.tdminhnhat.module.model.dto.ProductCategoryDto;
import io.github.tdminhnhat.module.model.vo.ProductCategoryVo;
import io.github.tdminhnhat.module.service.ProductCategoryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
