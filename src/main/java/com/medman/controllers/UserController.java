package com.medman.controllers;

import com.medman.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Created by jessedavila on 1/17/17.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/dashboard")
    public String showDash() {
        return null;
    }

    @GetMapping("/my_doctors")
    public String showMyDoctors(Model model) {
       // model.addAttribute("users", new User()); Need to call on user relationships between patient and doctor.
        // current logged in user's connected users should be called here and it should show their info.
        return null;
    }
}
