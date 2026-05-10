package io.github.tdminhnhat.module.entity;

import io.github.tdminhnhat.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity @Table(name = "product_sku_attributes")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class ProductSkuAttribute extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "product_sku_id", nullable = false) @NonNull
    ProductSku productSku;

    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "attribute_id", nullable = false) @NonNull
    Attribute attribute;

    @Column(name = "value", nullable = false, columnDefinition = "TEXT DEFAULT ''") @NonNull
    String value;
}
