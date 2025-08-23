package com.spring.portfolio.service;

import com.spring.portfolio.model.ContactForm;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

    public void sendEmail(ContactForm contactForm) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(from);
        helper.setTo(contactForm.getEmail());
        helper.setSubject("Follow-up to patron: " + contactForm.getName());

        String emailBody = "<html>" +
                "<body>" +
                "<p>Hi " + contactForm.getName() + ",</p>" +
                "<p>Thank you for visiting my website! I hope you enjoyed exploring my portfolio.</p>" +
                "<p>I have received your message:</p>" +
                "<blockquote><b>\"" + contactForm.getMessage() + "\"</b></blockquote>" +
                "<p>With your cellphone number: <b>" + contactForm.getPhone() + "</b>"+
                "<p>I will reach out to you soon.</p>" +
                "<p>I look forward to speaking with you!</p>" +
                "<p>Thank you once again for your interest.</p>" +
                "<p>Best regards,<br>Nayan A.</p>" +
                "</body>" +
                "</html>";

        helper.setText(emailBody, true);
        mailSender.send(mimeMessage);
    }
}
