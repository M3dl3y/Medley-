package com.medman.controllers;

import com.medman.models.Medication;
import com.medman.models.User;
import com.medman.repositories.MedicationRepository;
import com.medman.services.UserService;
import com.medman.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/dashboard")
    public String showDash(Model model) {
        model.addAttribute("user", userService.currentUser());
        return "shared/dashboard";
    }

    @GetMapping("/connections") // we can probably come up with a better uri than this.
    public String showMyDoctors(Model model) {
        model.addAttribute("user", userService.currentUser());
        // right now there is no table that connects patients to doctors so this is something we need to figure out to show
        // doctors on page.
        return "shared/viewLinkedUsers";
    }

    @GetMapping("/messages")
    public String showMessages(Model model) {
        model.addAttribute("user", userService.currentUser());
        return "shared/messages";
    }

    @GetMapping("/edit")
    public String editPage(Model model) {
        model.addAttribute("user", userService.currentUser());

        return "shared/profile"; // only a logged in user can go to user/edit

    }

    @PostMapping("/edit")
    public String editUserInfo(
            @Valid User user,
            Errors validation,
            Model model
    ) {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("user", user);
            return "posts/edit";
        }
        userService.changeProfileInfo(user);
        return "redirect:/user/dashboard";

    }
}
