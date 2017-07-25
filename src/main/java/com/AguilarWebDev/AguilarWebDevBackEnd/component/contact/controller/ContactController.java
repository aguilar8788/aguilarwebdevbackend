package com.AguilarWebDev.AguilarWebDevBackEnd.component.contact.controller;

import com.AguilarWebDev.AguilarWebDevBackEnd.component.MailUtil;
import com.AguilarWebDev.AguilarWebDevBackEnd.component.contact.model.Contact;
import com.AguilarWebDev.AguilarWebDevBackEnd.component.contact.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/contact")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin("https://aguilarwebdevelopment.com")
public class ContactController {

    @Autowired
    private ContactRepository contactRepo;

    @Autowired
    private MailUtil mailUtil;

    @RequestMapping(method= RequestMethod.GET)
    public List<Contact> getAll() {
        List<Contact> contact =  contactRepo.findAll();
        return contact;
    }

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity create(@RequestBody Contact contact) throws Exception {
        String contactName = contact.getFirstName() + " " + contact.getLastName();
        String contactPhoneNumber = contact.getPhoneNumber();
        String contactCompany = "";
        if(contact.getCompany() != null){
            contactCompany = contact.getCompany();
        }
//        SendGrid sendgrid = new SendGrid("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");
//        SendGrid.Email welcomeMail = new SendGrid.Email();
//        welcomeMail.addTo("aguilarwebdevelopment@gmail.com");
//        welcomeMail.addToName("Peter Aguilar Jr.");
//        welcomeMail.setFrom("contactRequest@aguilarwebdevelopment.com");
//        welcomeMail.setSubject("New Contact Request");
//        welcomeMail.setText("A new request for client contact has been created. Please follow up with " + contactName + " " + contactPhoneNumber + " " + contactCompany);
//
//        try {
//            SendGrid.Response response = sendgrid.send(welcomeMail);
//            System.out.println(response.getMessage());
//        } catch (SendGridException sge) {
//            sge.printStackTrace();
//        }


       MailUtil.send("contact@aguilarwebdevelopment.com", "aguilarwebdevelopment@gmail.com", "new contact request", contactName + " " + contactPhoneNumber + " " + contactCompany);
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
