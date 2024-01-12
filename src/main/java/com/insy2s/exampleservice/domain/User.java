package com.insy2s.exampleservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User extends AbstractAuditingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -1111993538667935519L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1, initialValue = 1000)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "uuid", nullable = false, unique = true)
    private UUID uuid;

    @NotBlank
    @Max(50)
    @Min(2)
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotBlank
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    /**
     * @Past annotation is used to validate that a date value
     * is strictly in the past and not today .
     */
    @Past
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @ToString.Exclude
    private Set<Address> addresses = new LinkedHashSet<>();

    @PrePersist
    public void prePersist() {
        this.uuid = UUID.randomUUID();
    }
}
