package com.hehetenya.phonecontacts.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    @NotBlank(message = "Name must not be blank.")
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "contact",
            cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Email> emails = new HashSet<>();

    @OneToMany(mappedBy = "contact",
            cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Phone> phones = new HashSet<>();

    public void setEmails(List<String> addresses){
        emails.clear();
        for (String s: addresses) {
            Email email = new Email();
            email.setAddress(s);
            email.setContact(this);
            emails.add(email);
        }
    }

    public void setPhones(List<String> numbers){
        phones.clear();
        for (String s: numbers) {
            Phone phone = new Phone();
            phone.setNumber(s);
            phone.setContact(this);
            phones.add(phone);
        }
    }
}
