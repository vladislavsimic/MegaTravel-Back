package com.xml.megatravel.model;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

import static com.xml.megatravel.util.ValidationConstants.SERVICE_NAME_SIZE;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "service")
@Where(clause = "is_deleted='false'")
public class Service extends BaseEntity {

    @NotBlank
    @Column(name = "name", unique = true)
    @Size(max = SERVICE_NAME_SIZE)
    private String name;

    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY)
    private Set<PropertyService> propertyServices;
}
