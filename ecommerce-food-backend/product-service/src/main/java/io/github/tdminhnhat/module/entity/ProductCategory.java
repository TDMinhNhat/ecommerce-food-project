package io.github.tdminhnhat.module.entity;

import io.github.tdminhnhat.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity @Table(name = "product_categories")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class ProductCategory extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_parent")
    ProductCategory productCategoryParent;

    @Column(name = "category_name", length = 100, nullable = false, unique = true) @NonNull
    String categoryName;

    @Column(name = "description")
    String description;

    @Column(name = "image_id", length = 50, unique = true)
    String imageId;

    public ProductCategory(ProductCategory productCategoryParent, @NonNull String categoryName, String description, String imageId) {
        this.productCategoryParent = productCategoryParent;
        this.categoryName = categoryName;
        this.description = description;
        this.imageId = imageId;
    }
}
