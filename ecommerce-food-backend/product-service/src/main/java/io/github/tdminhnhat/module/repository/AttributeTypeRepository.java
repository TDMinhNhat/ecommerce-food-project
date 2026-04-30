package io.github.tdminhnhat.module.repository;

import io.github.tdminhnhat.module.entity.AttributeType;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttributeTypeRepository extends ListCrudRepository<AttributeType, Long> {
    Optional<AttributeType> findByTypeName(String typeName);
}
