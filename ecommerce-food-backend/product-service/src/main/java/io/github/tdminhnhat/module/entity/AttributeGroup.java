package io.github.tdminhnhat.module.entity;

import io.github.tdminhnhat.core.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity @Table(name = "attribute_groups")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class AttributeGroup extends BaseEntity {

    @Column(name = "group_name", length = 150, nullable = false, unique = true) @NonNull
    String groupName;

    @Column(name = "group_type", length = 100, nullable = false, unique = true) @NonNull
    String groupType;
}
