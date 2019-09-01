package com.xml.megatravel.model;

import com.xml.megatravel.model.enumeration.Category;
import com.xml.megatravel.model.enumeration.Role;
import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.xml.megatravel.util.ValidationConstants.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Where(clause = "is_deleted='false'")
public class User extends BaseEntity implements UserDetails {

    @NotBlank
    @Column(name = "first_name")
    @Size(max = FIRST_NAME_SIZE)
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    @Size(max = LAST_NAME_SIZE)
    private String lastName;

    @NotBlank
    @Column(name = "username", unique = true)
    @Size(min = USERNAME_MIN_SIZE, max = USERNAME_MAX_SIZE)
    private String username;

    @NotBlank
    @Column(name = "password")
    @Size(max = PASSWORD_HASH_SIZE)
    private String password;

    @NotNull
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Category category;

    @Email
    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_picture_id")
    private Image profilePicture;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Reservation> reservations = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
