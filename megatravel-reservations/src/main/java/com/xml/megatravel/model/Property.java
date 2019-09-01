package com.xml.megatravel.model;

import com.xml.megatravel.model.enumeration.Category;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static com.xml.megatravel.util.ValidationConstants.PROPERTY_DESCRIPTION_SIZE;
import static com.xml.megatravel.util.ValidationConstants.PROPERTY_NAME_SIZE;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "property")
@Where(clause = "is_deleted='false'")
public class Property extends BaseEntity {

    @NotBlank
    @Size(max = PROPERTY_NAME_SIZE)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(max = PROPERTY_DESCRIPTION_SIZE)
    @Column(name = "description")
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @NotNull
    @PositiveOrZero
    @Column(name = "stars")
    private Integer stars;

    @NotNull
    @Column(name = "number_of_people")
    private Integer numberOfPeople;

    @NotNull
    @Column(name = "number_of_cancellation_days")
    private Integer numberOfCancellationDays;

    @NotNull
    @Column(name = "summer_price")
    private Double summerPrice;

    @NotNull
    @Column(name = "winter_price")
    private Double winterPrice;

    @NotNull
    @Column(name = "spring_price")
    private Double springPrice;

    @NotNull
    @Column(name = "autumn_price")
    private Double autumnPrice;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    @NotNull
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @OneToMany(mappedBy = "property", fetch = FetchType.EAGER)
    private Set<PropertyService> propertyServices = new HashSet<>();

    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY)
    private Set<Reservation> reservations = new HashSet<>();

}
