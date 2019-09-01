package com.xml.megatravel.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(of = {"id"})
public abstract class BaseEntity implements Persistable<UUID> {

    @Id
    @Column(name = "id")
    @Type(type = "uuid-char")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @NotNull
    @CreationTimestamp
    @Column(name = "time_created")
    private LocalDateTime timeCreated;

    @UpdateTimestamp
    @Column(name = "time_updated")
    private LocalDateTime timeUpdated;

    @PrePersist
    private void prePersist() {
        this.timeCreated = LocalDateTime.now();
        this.timeUpdated = LocalDateTime.now();
        this.isDeleted = false;
    }

    @Override
    public boolean isNew() {
        return this.getId() == null;
    }
}
