package com.xml.megatravel.model;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "property_service")
@Where(clause = "is_deleted='false'")
public class PropertyService extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;
}
