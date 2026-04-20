package io.github.tdminhnhat.core.model.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter @Setter
@NoArgsConstructor
public abstract class BaseVo {

    long id;

    String note;

    Instant createdDate;

    Instant lastModifiedDate;

    String createdBy;

    String lastModifiedBy;

    boolean deleted;

    long version;
}
