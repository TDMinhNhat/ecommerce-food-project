package io.github.tdminhnhat.module.repository;

import io.github.tdminhnhat.module.entity.AttributeGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttributeGroupRepository extends ListCrudRepository<AttributeGroup, Long> {

    @Query(value = "select * from attribute_groups ag where ag.group_name =:groupName or ag.group_type =:groupType", nativeQuery = true)
    Optional<AttributeGroup> getAttributeGroupByNameOrType(String groupName, String groupType);
}
