package io.github.tdminhnhat.module.repository;

import io.github.tdminhnhat.module.entity.ProductCategory;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends ListCrudRepository<ProductCategory, Long> {

}
