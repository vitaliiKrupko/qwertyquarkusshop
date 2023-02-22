package ua.krupko.vitalii.qwertyquarkusshop.model.entity;

import lombok.Getter;
import lombok.Setter;
import ua.krupko.vitalii.qwertyquarkusshop.model.entity.listener.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "created_date", nullable = false)
    private Instant createdDate;
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;
}
