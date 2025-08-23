package com.spring.portfolio.service;

import com.spring.portfolio.model.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.mailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String from;

    public void sendEmail(ContactForm contactForm) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(contactForm.getEmail());
        message.setSubject("Follow-up to patron: " + contactForm.getName());
        message.setText(
                "Hi " + contactForm.getName() + ",\n\n" +
                        "Thank you for visiting my website! I hope you enjoyed exploring my portfolio.\n\n" +
                        "I have received your message:\n" +
                        "\"" + contactForm.getMessage() + "\"\n\n" +
                        "With your cellphone number " + contactForm.getPhone() + ", \nI will reach out to you soon.\n" +
                        "I look forward to speaking with you!\n\n" +
                        "Thank you once again for your interest.\n\n" +
                        "Best regards,\n" +
                        "Nayan A."
        );
        mailSender.send(message);
    }
}
