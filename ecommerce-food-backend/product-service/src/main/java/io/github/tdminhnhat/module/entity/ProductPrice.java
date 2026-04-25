package io.github.tdminhnhat.module.entity;

import io.github.tdminhnhat.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity @Table(name = "product_prices")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class ProductPrice extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "product_id", nullable = false) @NonNull
    Product product;

    @Column(name = "price", nullable = false)
    double price;

    public ProductPrice(@NonNull Product product, @NonNull Double price) {
        this.product = product;
        this.price = price;
    }
}
