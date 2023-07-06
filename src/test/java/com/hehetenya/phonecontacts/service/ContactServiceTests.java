package com.hehetenya.phonecontacts.service;


import com.hehetenya.phonecontacts.dto.ContactDTO;
import com.hehetenya.phonecontacts.entity.Contact;
import com.hehetenya.phonecontacts.entity.User;
import com.hehetenya.phonecontacts.repository.ContactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ContactServiceTests {
    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    @Test
    public void testGetByUser() {
        // Arrange
        User currentUser = mockCurrentUser();


        when(contactRepository.getContactsByUser(currentUser)).thenReturn(currentUser.getContacts());

        // Act
        Set<ContactDTO> result = contactService.getByUser();

        // Assert
        assertEquals(5, result.size());


        // Additional assertions as needed
    }

    private User mockCurrentUser() {
        User user = new User();
        user.setLogin("user");
        user.setPassword("user");
        Set<Contact> contacts = new HashSet<>();
        for(int i = 0; i < 5; i++){
            contacts.add(mockContact(i));
        }
        user.setContacts(contacts);
        return user;
    }

    private Contact mockContact(int i) {
        Contact contact = new Contact();
        contact.setName("name" + i);
        contact.setEmails(mockEmails(i));
        contact.setPhones(mockPhones(i));
        return contact;
    }

    private List<String> mockEmails(int i) {
        List<String> emails = new ArrayList<>();
        for(int j = i; j < 3; j++){
            emails.add("email" + j + "@gmail.com");
        }
        return emails;
    }

    private List<String> mockPhones(int i) {
        List<String> phones = new ArrayList<>();
        for(int j = i; j < 3; j++){
            phones.add("+38068000000" + j);
        }
        return phones;
    }

    @Test
    public void testGetById() {
        // Arrange
        Long id = 1L;
        Contact contact = new Contact();
        when(contactRepository.getById(id)).thenReturn(Optional.of(contact));

        // Act
        ContactDTO result = contactService.getById(id);

        // Assert
        assertNotNull(result);
        // Additional assertions as needed
    }

    @Test
    public void testCreate() {
        // Arrange
        ContactDTO contactDTO = new ContactDTO();
        // Set up the necessary mocks and expectations

        // Act
        contactService.create(contactDTO);

        // Assert
        // Perform assertions to verify the expected behavior
    }

    @Test
    public void testDelete() {
        // Arrange
        Long id = 1L;
        // Set up the necessary mocks and expectations

        // Act
        contactService.delete(id);

        // Assert
        // Perform assertions to verify the expected behavior
    }

    @Test
    public void testUpdate() {
        // Arrange
        Long id = 1L;
        ContactDTO contactDTO = new ContactDTO();
        Contact contact = new Contact();
        when(contactRepository.getById(id)).thenReturn(Optional.of(contact));
        // Set up the necessary mocks and expectations

        // Act
        contactService.update(id, contactDTO);

        // Assert
        // Perform assertions to verify the expected behavior
    }

    // Additional tests for other methods can be added here
}

