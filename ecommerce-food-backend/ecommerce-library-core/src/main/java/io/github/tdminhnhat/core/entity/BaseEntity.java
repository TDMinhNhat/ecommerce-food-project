package io.github.tdminhnhat.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@MappedSuperclass
@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter @Setter
@NoArgsConstructor
@DynamicInsert @DynamicUpdate
public abstract class BaseEntity {

    @Id
    long id;

    @Column(name = "note")
    String note;

    @CreatedDate @Column(name = "created_date", nullable = false, updatable = false)
    Instant createdDate;

    @LastModifiedDate @Column(name = "last_modified_date", insertable = false)
    Instant lastModifiedDate;

    @CreatedBy @Column(name = "created_by", nullable = false, updatable = false)
    String createdBy;

    @LastModifiedBy @Column(name = "last_modified_by", insertable = false)
    String lastModifiedBy;

    @Column(name = "deleted", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    boolean deleted;

    @Version @Column(name = "version", nullable = false)
    long version;
}
