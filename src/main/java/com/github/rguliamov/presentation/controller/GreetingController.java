package com.github.rguliamov.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Guliamov Rustam
 */
@Controller
@RequestMapping("/")
public class GreetingController {

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }
}
