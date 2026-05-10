package io.github.tdminhnhat.module.entity;

import io.github.tdminhnhat.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity @Table(name = "product_attributes")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class ProductAttribute extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "product_id", nullable = false) @NonNull
    Product product;

    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "attribute_id", nullable = false) @NonNull
    Attribute attribute;

    @Column(name = "value", nullable = false, columnDefinition = "TEXT DEFAULT ''") @NonNull
    String value;
}
