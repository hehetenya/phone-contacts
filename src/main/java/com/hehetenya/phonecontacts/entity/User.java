package com.hehetenya.phonecontacts.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode(exclude = "contacts")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", unique = true, nullable = false)
    @NotBlank(message = "Login must not be blank.")
    private String login;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password must not be blank.")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Set<Contact> contacts;
}
