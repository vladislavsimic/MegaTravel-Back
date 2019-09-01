package com.xml.megatravel.model;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static com.xml.megatravel.util.ValidationConstants.AGENT_PIB_SIZE;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agent")
@Where(clause = "is_deleted='false'")
public class Agent extends BaseEntity {

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank
    @Column(name = "pib")
    @Size(max = AGENT_PIB_SIZE)
    private String pib;

    @NotNull
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "agent", fetch = FetchType.LAZY)
    private Set<Property> properties = new HashSet<>();
}
