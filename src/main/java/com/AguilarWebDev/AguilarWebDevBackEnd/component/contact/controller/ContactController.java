package com.AguilarWebDev.AguilarWebDevBackEnd.component.contact.controller;

import com.AguilarWebDev.AguilarWebDevBackEnd.component.contact.model.Contact;
import com.AguilarWebDev.AguilarWebDevBackEnd.component.contact.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@RestController
@RequestMapping("/contact")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin("https://aguilarwebdevelopment-63428.firebaseapp.com")
public class ContactController {

    @Autowired
    private ContactRepository contactRepo;

    @RequestMapping(method= RequestMethod.GET)
    public List<Contact> getAll() {
        List<Contact> contact =  contactRepo.findAll();
        return contact;
    }

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity create(@RequestBody Contact contact) {

         return ResponseEntity.ok(
        contactRepo.save(contact));
    }

    @RequestMapping(method= RequestMethod.DELETE, value="{id}")
    public List<Contact> delete(@PathVariable String id) {
        contactRepo.delete(id);
        return contactRepo.findAll();
    }

    @RequestMapping(method=RequestMethod.PUT, value="{id}")
    public Contact update(@PathVariable String id, @RequestBody Contact contact) {
        Contact update = contactRepo.findOne(id);

        update.setFirstName(contact.getFirstName());
        update.setLastName(contact.getLastName());
        update.setPhoneNumber(contact.getPhoneNumber());
        update.setCompany(contact.getCompany());

        return contactRepo.save(update);
    }
}
