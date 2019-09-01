package com.xml.megatravel.model;

import com.xml.megatravel.model.enumeration.ImageType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
@Where(clause = "is_deleted='false'")
public class Image extends BaseEntity {

    @NotNull
    @Type(type = "uuid-char")
    @Column(name = "entity_id")
    private UUID entityId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type")
    private ImageType entityType;

    @NotNull
    @Column(name = "url")
    private String url;
}
