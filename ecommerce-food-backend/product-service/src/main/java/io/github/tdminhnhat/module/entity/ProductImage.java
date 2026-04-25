package io.github.tdminhnhat.module.entity;

import io.github.tdminhnhat.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity @Table(name = "product_images")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
@NoArgsConstructor
public class ProductImage extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "product_id", nullable = false) @NonNull
    Product product;

    @Column(name = "image_id", length = 50, nullable = false, unique = true) @NonNull
    String imageId;

    @Column(name = "is_main", nullable = false)
    boolean isMain;

    @Column(name = "is_displayed", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    boolean isDisplayed;

    public ProductImage(@NonNull Product product, @NonNull String imageId, Boolean isMain, Boolean isDisplayed) {
        this.product = product;
        this.imageId = imageId;
        this.isMain = isMain != null ? isMain : false;
        this.isDisplayed = isDisplayed != null ? isDisplayed : true;
    }
}
