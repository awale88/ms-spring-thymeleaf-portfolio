package com.spring.portfolio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "portfolio")
@Data
public class ContactForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;

    @NotEmpty(message = "Phone is required")
    private String phone;

    @Size(max = 500, message = "Message cannot exceed 500 characters")
    private String message;

}
