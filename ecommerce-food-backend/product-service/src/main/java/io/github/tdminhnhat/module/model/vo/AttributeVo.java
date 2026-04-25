package io.github.tdminhnhat.module.model.vo;

import io.github.tdminhnhat.core.model.vo.BaseVo;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AttributeVo extends BaseVo {

    String attributeId;

    String attributeName;

    String description;
}
