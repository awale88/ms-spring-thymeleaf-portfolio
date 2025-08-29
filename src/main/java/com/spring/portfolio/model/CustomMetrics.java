package com.spring.portfolio.model;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class CustomMetrics {

    private final Counter contactFormSubmissions;
    private final Counter emailSendAttempts;
    private final Counter emailSendErrors;


    public CustomMetrics(MeterRegistry registry){
        this.contactFormSubmissions = Counter.builder("portfolio.contact.submissions")
                .description("Total contact form submissions")
                .register(registry);

        this.emailSendAttempts = Counter.builder("portfolio.email.attempts")
                .description("Total email sending attempts")
                .register(registry);

        this.emailSendErrors = Counter.builder("portfolio.email.errors")
                .description("Total email sending errors")
                .register(registry);
    }

    public void recordContactSubmission(){
        contactFormSubmissions.increment();
    }

    public void recordEmailAttempts(){
        emailSendAttempts.increment();
    }
    public void recordEmailErrors(){
        emailSendErrors.increment();
    }
}
