package io.github.tdminhnhat.module.entity;

import io.github.tdminhnhat.core.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity @Table(name = "attribute_group_items")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class AttributeGroupItem extends BaseEntity {

    @ManyToOne @JoinColumn(name = "attribute_id", nullable = false) @NonNull
    Attribute attribute;

    @ManyToOne @JoinColumn(name = "attribute_group_id", nullable = false) @NonNull
    AttributeGroup attributeGroup;
}
