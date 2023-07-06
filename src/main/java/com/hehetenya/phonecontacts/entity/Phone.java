package com.hehetenya.phonecontacts.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "phones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false)
    @NotBlank(message = "Number must not be blank.")
    @Pattern(regexp = "^\\+\\d{12}$")
    private String number;

    @ManyToOne(optional = false)
    @JoinColumn(name = "contact_id")
    private Contact contact;
}
