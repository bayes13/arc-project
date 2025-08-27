package org.acme.entity;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.slf4j.MDC;

import java.time.LocalDateTime;

public class AuditListener {

    @PrePersist
    public void beforePersist(BaseEntity entity) {
        final String createdBy = MDC.get("createdBy");
        entity.setCreatedTimestamp(LocalDateTime.now());
        entity.setCreatedBy(createdBy);
    }

    @PreUpdate
    public void beforeUpdate(BaseEntity entity) {
        final String updatedBy = MDC.get("createdBy");
        entity.setUpdatedTimestamp(LocalDateTime.now());
        entity.setUpdatedBy(updatedBy);
    }
}
