package com.hehetenya.phonecontacts.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "emails")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address", nullable = false)
    @NotBlank(message = "Email must not be blank.")
    @javax.validation.constraints.Email
    private String address;

    @ManyToOne(optional = false)
    @JoinColumn(name = "contact_id")
    private Contact contact;
}
