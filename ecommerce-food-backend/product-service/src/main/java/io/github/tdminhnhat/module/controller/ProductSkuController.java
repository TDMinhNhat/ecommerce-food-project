package io.github.tdminhnhat.module.controller;

import io.github.tdminhnhat.module.model.dto.ProductSkuDto;
import io.github.tdminhnhat.module.model.vo.ProductSkuVo;
import io.github.tdminhnhat.module.service.ProductSkuService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${server.api.product-sku}")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductSkuController {

    ProductSkuService productSkuService;

    @PostMapping("/")
    public ResponseEntity<ProductSkuVo> add(@Valid @RequestBody ProductSkuDto request) throws Exception {
        return ResponseEntity.ok(productSkuService.save(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductSkuVo> delete(Long id) throws Exception {
        return ResponseEntity.ok(productSkuService.delete(id));
    }
}
