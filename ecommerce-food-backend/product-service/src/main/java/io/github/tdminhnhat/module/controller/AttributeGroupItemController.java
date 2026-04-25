package io.github.tdminhnhat.module.controller;

import io.github.tdminhnhat.module.model.dto.AttributeGroupItemDto;
import io.github.tdminhnhat.module.model.vo.AttributeGroupItemVo;
import io.github.tdminhnhat.module.service.AttributeGroupItemService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${server.api.attribute-group-item}")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AttributeGroupItemController {

    AttributeGroupItemService attributeGroupItemService;

    @PostMapping("/")
    public ResponseEntity<AttributeGroupItemVo> add(@Valid @RequestBody AttributeGroupItemDto request) throws Exception{
        return ResponseEntity.ok(attributeGroupItemService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttributeGroupItemVo> update(@PathVariable Long id, @Valid @RequestBody AttributeGroupItemDto request) throws Exception {
        return ResponseEntity.ok(attributeGroupItemService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AttributeGroupItemVo> delete(@PathVariable Long id) throws Exception{
        return ResponseEntity.ok(attributeGroupItemService.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttributeGroupItemVo> findById(@PathVariable Long id) {
        return ResponseEntity.ok(attributeGroupItemService.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<AttributeGroupItemVo>> findAll() {
        return ResponseEntity.ok(attributeGroupItemService.findAll());
    }
}
