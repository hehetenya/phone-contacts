package com.hehetenya.phonecontacts.controller;

import com.hehetenya.phonecontacts.dto.ContactDTO;
import com.hehetenya.phonecontacts.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.Set;

@RestController
@RequestMapping("/contacts")
@AllArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @GetMapping
    public Set<ContactDTO> getAllByUser() {
        return contactService.getByUser();
    }

    @GetMapping("/{id}")
    public ContactDTO getById(@PathVariable Long id) {
        return contactService.getById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createContact(@RequestBody ContactDTO contactDTO) {
        contactService.create(contactDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteContact(@PathVariable Long id) {
        contactService.delete(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateContact(@PathVariable Long id,
                              @RequestBody ContactDTO contactDTO) {
        contactService.update(id, contactDTO);
    }
}
