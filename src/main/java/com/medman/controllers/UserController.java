package com.medman.controllers;

import com.medman.models.AppointmentTime;
import com.medman.models.Medication;
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
public class UserController extends BaseController {

    @GetMapping("/dashboard")
    public String showDash(Model model) {
        model.addAttribute("meds", new Medication());
        model.addAttribute("dates", new AppointmentTime());
        // need to add objects for alerts, meds, and dates, so 3 model.addAttribute?
        // are alerts something that is calculated?
        return null;
    }

    @GetMapping("/my_doctors")
    public String showMyDoctors(Model model) {
        // model.addAttribute("users", new User()); Need to call on user relationships between patient and doctor.
        // current logged in user's connected users should be called here and it should show their info.
        // it looks like you would b
        return null;
    }

    @GetMapping("/messages")
    public String showMessages(Model model) {
        //model.addAttribute("") are we making objects for all of these different tables? we must be? so a message instance is passed here?
        //model.addAttribute() and also a user object, this will be fairly complicated to show many message streams and select one to show more messages
        // bring in the messages instance and use a setUser method to connect LoggedInUser to this messages instance.
        return null;
    }

    @GetMapping("/edit")
    public String editPage(Model model) {
        model.addAttribute("user", new User()); // need to call logged in User
        return null; // only a logged in user can go to user/edit

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
