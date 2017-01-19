package com.medman.controllers;

import com.medman.models.Medication;
import com.medman.models.User;
import com.medman.repositories.MedicationRepository;
import com.medman.services.UserService;
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
    MedicationRepository medsDAO;

    @Autowired
    private UserService userService;

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
        //model.addAttribute("") are we making objects for all of these different tables? we must be? so a message instance is passed here?
        //model.addAttribute() and also a user object, this will be fairly complicated to show many message streams and select one to show more messages
        return "shared/messages";
    }

    @GetMapping("/edit")
    public String editPage(Model model) {
        model.addAttribute("user", new User()); // need to call logged in User
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

        //User existingUser = (use autowired dao to get this)
        // use dao to get current existingUser
        //        existingPost.setTitle(editedPost.getTitle()); (template code)
        return "redirect:/user/dashboard";

    }
}
