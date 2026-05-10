package io.github.tdminhnhat.module.repository;

import io.github.tdminhnhat.module.entity.ProductMedia;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMediaRepository extends ListCrudRepository<ProductMedia, Long> {
}
