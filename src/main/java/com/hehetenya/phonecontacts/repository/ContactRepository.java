package com.hehetenya.phonecontacts.repository;

import com.hehetenya.phonecontacts.entity.Contact;
import com.hehetenya.phonecontacts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Set<Contact> getContactsByUser(User user);
    Optional<Contact> getById(Long id);
    void removeById(Long id);
}
