package com.spring.portfolio.service;

import com.spring.portfolio.model.ContactForm;
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

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ContactRepository repository;

    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender javaMailSender, ContactRepository repository, TemplateEngine templateEngine) {
        this.mailSender = javaMailSender;
        this.repository = repository;
        this.templateEngine = templateEngine;
    }

    @Value("${spring.mail.username}")
    private String from;

    public boolean sendEmail(ContactForm contactForm) throws MessagingException {
        try {
            Context context = new Context();
            context.setVariable("name", contactForm.getName());
            context.setVariable("message", contactForm.getMessage());
            context.setVariable("phone", contactForm.getPhone());

            String emailBody = templateEngine.process("/email/confirmation.html", context);

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
        } catch (Exception e) {
            log.error("Unexpected error while sending email to {}: {}", contactForm.getEmail(), e.getMessage());
        }
        return false;
    }
}
