package io.github.tdminhnhat.module.repository;

import io.github.tdminhnhat.module.entity.ProductSku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductSkuRepository extends JpaRepository<ProductSku, Long> {

    List<ProductSku> findBySkuCodeIn(Collection<String> skuCodes);

    @Modifying
    @NativeQuery("update product_skues set deleted = true where product_id =:productId")
    int deleteByProductId(long productId);
}
