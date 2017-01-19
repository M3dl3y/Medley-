package com.medman.controllers;

import com.medman.models.User;
import com.medman.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.medman.services.UserService;

import javax.validation.Valid;

/**
 * Created by jessedavila on 1/17/17.
 */
@Controller
@RequestMapping("/")
public class VisitorController {


    @Autowired
    private UserService userService;

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
        return "register";
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
        user.setUsername(StringUtils.trimWhitespace(user.getUsername()));
        user.setEmail(StringUtils.trimWhitespace(user.getEmail()));
        userService.register(user);
        userService.authenticate(user);
        return "shared/dashboard"; // return to dashboard
    }
}
