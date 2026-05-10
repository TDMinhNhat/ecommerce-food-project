package io.github.tdminhnhat.module.controller;

import io.github.tdminhnhat.module.model.dto.ProductDto;
import io.github.tdminhnhat.module.model.vo.ProductVo;
import io.github.tdminhnhat.module.service.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${server.api.product}")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductController {

    ProductService productService;

    @PostMapping("/")
    public ResponseEntity<ProductVo> add(@Valid @RequestBody ProductDto request) throws Exception {
        return ResponseEntity.ok(productService.save(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductVo> delete(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(productService.delete(id));
    }
}
