package com.insy2s.exampleservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Address extends AbstractAuditingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 2895067613277727952L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    @SequenceGenerator(name = "address_seq", sequenceName = "address_seq", allocationSize = 1, initialValue = 1000)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "uuid", nullable = false, unique = true)
    private UUID uuid;

    @NotBlank
    @Column(name = "street", nullable = false, length = 50)
    private String street;

    @NotBlank
    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @NotBlank
    @Column(name = "state", nullable = false, length = 50)
    private String state;

    @NotBlank
    @Column(name = "zip", nullable = false, length = 50)
    private String zip;

    @NotBlank
    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void prePersist() {
        this.uuid = UUID.randomUUID();
    }
}
