package io.github.tdminhnhat.module.repository;

import io.github.tdminhnhat.module.entity.ProductSkuAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSkuAttributeRepository extends JpaRepository<ProductSkuAttribute, Long> {
}
