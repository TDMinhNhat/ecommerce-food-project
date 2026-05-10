package io.github.tdminhnhat.module.entity;

import io.github.tdminhnhat.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity @Table(name = "product_skues")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class ProductSku extends BaseEntity {

    @ManyToOne @JoinColumn(name = "product_id", nullable = false) @NonNull
    Product product;

    @Column(name = "sku_code", length = 50, nullable = false, unique = true) @NonNull
    String skuCode;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<ProductSkuAttribute> productSkuAttributes;
}
