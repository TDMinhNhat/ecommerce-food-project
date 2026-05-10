package io.github.tdminhnhat.module.entity;

import io.github.tdminhnhat.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity @Table(name = "products")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class Product extends BaseEntity {

    @Column(name = "product_id", length = 50, nullable = false, unique = true) @NonNull
    String productId;

    @Column(name = "product_name", length = 100, nullable = false, unique = true) @NonNull
    String productName;

    @Column(name = "product_description", nullable = false, columnDefinition = "TEXT DEFAULT ''") @NonNull
    String productDescription;

    @ManyToOne(cascade = CascadeType.ALL)  @JoinColumn(name = "product_category_id", nullable = false) @NonNull
    ProductCategory productCategory;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<ProductSku> productSkus;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<ProductAttribute> productAttributes;
}
