package com.axenovo.infotech.controller;

import org.springframework.stereotype.Controller;

@Controller
public class Home {

    @GetMapping("/")
    public String home() {
        return "home";
    }
    
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/services")
    public String services() {  
        return "services";
    }

}
