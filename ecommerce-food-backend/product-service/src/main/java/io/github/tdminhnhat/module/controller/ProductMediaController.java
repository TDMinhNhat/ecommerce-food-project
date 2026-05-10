package io.github.tdminhnhat.module.controller;

import io.github.tdminhnhat.module.model.dto.ProductMediaDto;
import io.github.tdminhnhat.module.model.vo.ProductMediaVo;
import io.github.tdminhnhat.module.service.ProductMediaService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${server.api.product-media}")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductMediaController {

    ProductMediaService productMediaService;

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductMediaVo> add(@Valid @RequestBody ProductMediaDto request) throws Exception {
        return ResponseEntity.ok(productMediaService.save(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductMediaVo> delete(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(productMediaService.delete(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductMediaVo>> getAll() {
        return ResponseEntity.ok(productMediaService.findAll());
    }
}
