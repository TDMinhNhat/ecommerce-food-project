package io.github.tdminhnhat.module.entity;

import io.github.tdminhnhat.core.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity @Table(name = "products")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class Product extends BaseEntity {

    @Column(name = "product_id", length = 30, nullable = false, unique = true) @NonNull
    String productId;

    @Column(name = "sku_code", length = 50, nullable = false, unique = true) @NonNull
    String skuCode;

    @Column(name = "product_name", length = 200, nullable = false, unique = true) @NonNull
    String productName;

    @Column(name = "brand", length = 50, nullable = false) @NonNull
    String brand;

    @Column(name = "unit", length = 50, nullable = false) @NonNull
    String unit;

    @Column(name = "description")
    String description;
}
