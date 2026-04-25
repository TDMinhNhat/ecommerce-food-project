package io.github.tdminhnhat.module.controller;

import io.github.tdminhnhat.module.model.dto.AttributeGroupDto;
import io.github.tdminhnhat.module.model.vo.AttributeGroupVo;
import io.github.tdminhnhat.module.service.AttributeGroupService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${server.api.attribute-group}")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AttributeGroupController {

    AttributeGroupService attributeGroupService;

    @PostMapping("/")
    public ResponseEntity<AttributeGroupVo> add(@Valid @RequestBody AttributeGroupDto request) throws Exception {
        return ResponseEntity.ok(attributeGroupService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttributeGroupVo> update(@PathVariable Long id, @Valid @RequestBody AttributeGroupDto request) throws Exception {
        return ResponseEntity.ok(attributeGroupService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AttributeGroupVo> delete(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(attributeGroupService.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttributeGroupVo> findById(@PathVariable Long id) {
        return ResponseEntity.ok(attributeGroupService.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<AttributeGroupVo>> findAll() {
        return ResponseEntity.ok(attributeGroupService.findAll());
    }
}
