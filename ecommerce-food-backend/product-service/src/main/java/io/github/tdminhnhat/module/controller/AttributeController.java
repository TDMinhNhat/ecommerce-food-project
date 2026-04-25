package io.github.tdminhnhat.module.controller;

import io.github.tdminhnhat.module.model.dto.AttributeDto;
import io.github.tdminhnhat.module.model.vo.AttributeVo;
import io.github.tdminhnhat.module.service.AttributeService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${server.api.attribute}")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AttributeController {

    AttributeService attributeService;

    @PostMapping("/")
    public ResponseEntity<AttributeVo> add(@Valid @RequestBody AttributeDto request) throws Exception {
        return ResponseEntity.ok(attributeService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttributeVo> update(@PathVariable Long id, @Valid @RequestBody AttributeDto request) throws Exception {
        return ResponseEntity.ok(attributeService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AttributeVo> delete(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(attributeService.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttributeVo> findById(@PathVariable Long id) {
        return ResponseEntity.ok(attributeService.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<AttributeVo>> findAll() {
        return ResponseEntity.ok(attributeService.findAll());
    }
}
