package com.hehetenya.phonecontacts.service;

import com.hehetenya.phonecontacts.dto.ContactDTO;
import com.hehetenya.phonecontacts.entity.Contact;
import com.hehetenya.phonecontacts.entity.Email;
import com.hehetenya.phonecontacts.entity.Phone;
import com.hehetenya.phonecontacts.entity.User;
import com.hehetenya.phonecontacts.repository.ContactRepository;
import com.hehetenya.phonecontacts.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    public Set<ContactDTO> getByUser(){
        Set<Contact> contacts = contactRepository.getContactsByUser(getCurrentUser());
        Set<ContactDTO> contactDTOSet = new HashSet<>();
        for (Contact c: contacts) {
            contactDTOSet.add(mapContactDTO(c));
        }
        return contactDTOSet;
    }

    public ContactDTO getById(Long id) {
        Optional<Contact> optionalContact = contactRepository.getById(id);
        checkUserRights(optionalContact);
        return mapContactDTO(optionalContact.get());
    }

    @Transactional
    public void create(ContactDTO contactDTO) {
        Contact contact = new Contact();
        removeDuplicateEmailsAndPhones(contactDTO);
        setAllFields(contact, contactDTO);
        contactRepository.save(contact);
    }

    @Transactional
    public void delete(Long id) {
        System.out.println(id);
        contactRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, ContactDTO contactDTO) {
        Optional<Contact> optionalContact = contactRepository.getById(id);
        checkUserRights(optionalContact);
        removeDuplicateEmailsAndPhones(contactDTO);
        Contact contact = optionalContact.get();

        setAllFields(contact, contactDTO);
    }

    private void checkUserRights(Optional<Contact> optionalContact){
        if(optionalContact.isEmpty() || !optionalContact.get().getUser().equals(getCurrentUser())){
            throw  new IllegalArgumentException(
                    "There is no contact with such id.");
        }
    }

    private void setAllFields(Contact contact, ContactDTO contactDTO){
        contact.setUser(getCurrentUser());
        contact.setName(contactDTO.getName());
        contact.setEmails(contactDTO.getEmails());
        contact.setPhones(contactDTO.getPhones());
    }

    private ContactDTO mapContactDTO(Contact contact){
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setName(contact.getName());

        List<String> emails = new LinkedList<>();
        for (Email e: contact.getEmails()) {
            emails.add(e.getAddress());
        }
        contactDTO.setEmails(emails);

        List<String> phones = new LinkedList<>();
        for (Phone p: contact.getPhones()) {
            phones.add(p.getNumber());
        }
        contactDTO.setPhones(phones);
        return contactDTO;
    }

    private static User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userDetails.getUser();
    }

    private void removeDuplicateEmailsAndPhones(ContactDTO contactDTO){
        contactDTO.setEmails(new ArrayList<>(new HashSet<>(contactDTO.getEmails())));
        contactDTO.setPhones(new ArrayList<>(new HashSet<>(contactDTO.getPhones())));
    }
}
