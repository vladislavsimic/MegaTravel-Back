package com.xml.megatravel.model;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "type")
@Where(clause = "is_deleted='false'")
public class Type extends BaseEntity {

    @NotBlank
    @Column(name = "name", unique = true)
    private String name;

}
