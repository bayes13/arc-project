package org.acme.entity;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.acme.model.BaseModel;
import org.slf4j.MDC;

import java.time.LocalDateTime;

public class AuditListener {

    @PrePersist
    public void beforePersist(BaseModel entity) {
        final String createdBy = MDC.get("createdBy");
        entity.setCreatedTimestamp(LocalDateTime.now());
        entity.setCreatedBy(createdBy);
    }

    @PreUpdate
    public void beforeUpdate(BaseModel entity) {
        final String updatedBy = MDC.get("createdBy");
        entity.setUpdatedTimestamp(LocalDateTime.now());
        entity.setUpdatedBy(updatedBy);
    }
}
