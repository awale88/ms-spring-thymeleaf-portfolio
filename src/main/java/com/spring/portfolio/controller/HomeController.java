package com.spring.portfolio.controller;

import com.spring.portfolio.model.ContactForm;
import com.spring.portfolio.service.ContactService;
import com.spring.portfolio.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ContactService contactService;

    @GetMapping({"/", "", "/index"})
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/projects")
    public String showProjects() {
        return "projects";
    }

    @GetMapping("/resume")
    public String showResume() {
        return "resume";
    }

    @GetMapping("/contact")
    public String showContact(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "contact";
    }

    @GetMapping("/terms")
    public String showTerms() {
        return "terms";
    }

    @GetMapping("/privacy")
    public String showPrivacy() {
        return "privacy";
    }

}
