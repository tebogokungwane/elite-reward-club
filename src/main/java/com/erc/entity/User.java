package com.erc.entity;

import com.erc.enumerators.Gender;
import com.erc.enumerators.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(
                name = "emailid_unique",
                columnNames = "email_address"
        )
)
public class User implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "company_entity_sequence",


            sequenceName = "company_entity_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "company_entity_sequence")
    private Long id;
    private String firstName;
    private String lastName;
    @Column(name = "email_address", nullable = false)
    private String emailAddress;
    @Column(name = "password", nullable = false)
    private String password;
    private String phoneNumber;
    @Column(name = "created_at")
    private LocalDateTime localDateTime;
    @Column(name = "formatted_created_at")
    private String formattedDate;
    private boolean isActive;
    @Enumerated(EnumType.STRING)
    @JsonProperty
    private Gender gender;
    @Enumerated(EnumType.STRING)
    @JsonProperty
    private Role role;
    private String registeredByUser;
    @Column(name = "reset_token")
    private String resetToken;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            optional = false
    )
    private CustomerReward customerReward;

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        this.formattedDate = formatDateTime(localDateTime);
    }

    private String formatDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
        return localDateTime.format(formatter);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }


    @Override
    public String getUsername() {
        return emailAddress;
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
        return true;
    }
}
