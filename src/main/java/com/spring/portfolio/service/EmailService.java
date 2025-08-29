package com.spring.portfolio.service;

import com.spring.portfolio.model.ContactForm;
import com.spring.portfolio.model.CustomMetrics;
import com.spring.portfolio.repository.ContactRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.stereotype.Service;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@Slf4j
public class EmailService {

    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender mailSender;
    private final ContactRepository repository;
    private final CustomMetrics customMetrics;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, ContactRepository repository, TemplateEngine templateEngine,
                        CustomMetrics customMetrics) {
        this.mailSender = javaMailSender;
        this.repository = repository;
        this.templateEngine = templateEngine;
        this.customMetrics = customMetrics;
    }

    public boolean sendEmail(ContactForm contactForm) throws MessagingException {
        try {
            customMetrics.recordEmailAttempts();

            Context context = new Context();
            context.setVariable("name", contactForm.getName());
            context.setVariable("message", contactForm.getMessage());
            context.setVariable("phone", contactForm.getPhone());

            String emailBody = templateEngine.process("email/confirmation.html", context);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(from);
            helper.setTo(contactForm.getEmail());
            helper.setBcc(from);
            helper.setSubject("Follow-up to patron : " + contactForm.getName());
            helper.setText(emailBody, true);
            log.info("Email successful sent to subscriber {}", contactForm.getEmail());
            mailSender.send(mimeMessage);
            return true;

        } catch (MessagingException ex) {
            log.error("Messaging Exception while sending email to {}: {}", contactForm.getEmail(), ex.getMessage());
            customMetrics.recordEmailErrors();
        } catch (Exception e) {
            log.error("Unexpected error while sending email to {}: {}", contactForm.getEmail(), e.getMessage());
            customMetrics.recordEmailErrors();
        }
        return false;
    }
}
