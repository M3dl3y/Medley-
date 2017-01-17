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
@RequestMapping("/")
public class VisitorController {

    @GetMapping("/")
    public String splashPage() {
        return "splash_page"; // need to direct to splash page
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about"; // direct to about page
    }

    @GetMapping("/create")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return null; // return to user register page.
    }

    @PostMapping("/create")
    public String createUser(
            @Valid User user,
            Errors validation,
            Model model
    ) {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("user", user);
            return "user/create";
        }
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        //usersDao.save(user);
        return null; // redirect to splash page
    }
}
