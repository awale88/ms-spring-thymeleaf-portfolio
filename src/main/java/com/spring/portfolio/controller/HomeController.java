package com.spring.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

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
    public String showContact() {
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
