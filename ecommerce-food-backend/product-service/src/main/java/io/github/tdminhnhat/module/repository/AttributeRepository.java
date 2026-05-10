package io.github.tdminhnhat.module.repository;

import io.github.tdminhnhat.module.entity.Attribute;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeRepository extends ListCrudRepository<Attribute, Long> {

}
