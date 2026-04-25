package io.github.tdminhnhat.module.repository;

import io.github.tdminhnhat.module.entity.AttributeGroupItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeGroupItemRepository extends ListCrudRepository<AttributeGroupItem, Long> {

    @Query(value = "select agi from attribute_group_items agi where agi.attribute_id =:attributeId", nativeQuery = true)
    AttributeGroupItem getAttributeGroupItemByAttributeId(Long attributeId);
}
