package io.github.tdminhnhat.module.entity;

import io.github.tdminhnhat.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity @Table(name = "product_medias")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class ProductMedia extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "product_id", nullable = false) @NonNull
    Product product;

    @Column(name = "media_id", length = 50, nullable = false, unique = true) @NonNull
    String mediaId;

    @Column(name = "is_main_displayed", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    boolean isMainDisplayed;
}
