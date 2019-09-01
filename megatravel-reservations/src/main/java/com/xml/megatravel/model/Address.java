package com.xml.megatravel.model;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;

import static com.xml.megatravel.util.ValidationConstants.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
@Where(clause = "is_deleted='false'")
public class Address extends BaseEntity {

    @NotBlank
    @Size(max = STREET_NAME_SIZE)
    @Column(name = "street")
    private String street;

    @NotBlank
    @Size(max = CITY_NAME_SIZE)
    @Column(name = "city")
    private String city;

    @NotBlank
    @Size(max = COUNTRY_NAME_SIZE)
    @Column(name = "country")
    private String country;

    @NotNull
    @DecimalMin(value = LONGITUDE_MIN)
    @DecimalMax(value = LONGITUDE_MAX)
    @Column(name = "longitude")
    private Double longitude;

    @NotNull
    @DecimalMin(value = LATITUDE_MIN)
    @DecimalMax(value = LATITUDE_MAX)
    @Column(name = "latitude")
    private Double latitude;
}
