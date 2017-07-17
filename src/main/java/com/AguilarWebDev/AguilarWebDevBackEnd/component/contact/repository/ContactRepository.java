package com.AguilarWebDev.AguilarWebDevBackEnd.component.contact.repository;

import com.AguilarWebDev.AguilarWebDevBackEnd.component.contact.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by peteraguilar on 6/27/17.
 */
public interface ContactRepository extends MongoRepository<Contact, String>{

}
