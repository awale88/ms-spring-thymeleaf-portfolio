package com.spring.portfolio.config;

import com.spring.portfolio.model.ContactForm;
import com.spring.portfolio.service.ContactService;
import com.spring.portfolio.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contacts")
@Slf4j
public class ContactController {

    @Autowired
    EmailService emailService;

    @Autowired
    ContactService contactService;

    @PostMapping("/contact")
    @Operation(
            summary = "Submit a contact form via API",
            description = "Saves contact form and sends email",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Form submitted successfully",
                            content = @Content(schema = @Schema(implementation = ContactForm.class))),
                    @ApiResponse(responseCode = "500", description = "Server error")
            })
    public String submitContact(@ModelAttribute("contactForm") ContactForm contactForm, Model model) {
        try {
            log.info("Successfully saved user into db.");
            contactService.saveUser(contactForm);
            log.info("Email was sent successfully.");
            emailService.sendEmail(contactForm);
            model.addAttribute("success", true);
            model.addAttribute("contactForm", new ContactForm());
        } catch (Exception e) {
            log.error("Unexpected error: {} ", e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", true);
        }
        return "contact";
    }
}
