package com.spring.portfolio.repository;

import com.spring.portfolio.model.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactForm, Long> {
}
