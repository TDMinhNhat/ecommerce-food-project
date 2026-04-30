package io.github.tdminhnhat.module.entity;

import io.github.tdminhnhat.core.entity.BaseEntity;
import io.github.tdminhnhat.module.model.dto.AttributeTypeDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity @Table(name = "attributes")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class Attribute extends BaseEntity {

    @Column(name = "attribute_name", length = 100, nullable = false, unique = true) @NonNull
    String attributeName;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "attribute_type_id", nullable = false) @NonNull
    AttributeType attributeType;

    @Column(name = "description")
    String description;
}
