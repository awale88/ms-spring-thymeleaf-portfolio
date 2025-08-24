package com.spring.portfolio.service;

import com.spring.portfolio.model.ContactForm;
import com.spring.portfolio.repository.ContactRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    @Transactional
    public ContactForm saveUser(ContactForm contactForm){
            return repository.save(contactForm);
    }
}
