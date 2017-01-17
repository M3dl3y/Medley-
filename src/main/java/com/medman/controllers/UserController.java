package com.medman.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by jessedavila on 1/17/17.
 */
@Controller
public class UserController {

    @GetMapping("/create")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return null; // return to user register page.
    }
}
