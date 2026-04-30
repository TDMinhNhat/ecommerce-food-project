package io.github.tdminhnhat.module.model.vo;

import io.github.tdminhnhat.core.model.vo.BaseVo;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AttributeTypeVo extends BaseVo {

    String typeName;
}
