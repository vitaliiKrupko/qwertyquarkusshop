package ua.krupko.vitalii.qwertyquarkusshop.model.entity.listener;

import ua.krupko.vitalii.qwertyquarkusshop.model.entity.AbstractEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Instant;

public class AuditingEntityListener {
    @PrePersist
    void preCreate(AbstractEntity auditable) {
        Instant now = Instant.now();
        auditable.setCreatedDate(now);
        auditable.setLastModifiedDate(now);
    }
    @PreUpdate
    void preUpdate(AbstractEntity auditable) {
        Instant now = Instant.now();
        auditable.setLastModifiedDate(now);
    }
}
