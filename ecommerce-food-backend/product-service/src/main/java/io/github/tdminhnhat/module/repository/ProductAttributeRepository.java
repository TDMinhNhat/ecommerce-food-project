package io.github.tdminhnhat.module.repository;

import io.github.tdminhnhat.module.entity.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Long> {

    @Modifying
    @NativeQuery("update product_attributes set deleted = true where product_id =:productId")
    int deleteAllByProduct(long productId);

    List<ProductAttribute> findProductAttributesByProduct_Id(long productId);
}
