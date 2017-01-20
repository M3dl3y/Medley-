package com.medman.controllers;

import com.medman.models.Role;
import com.medman.models.Roles;
import com.medman.models.User;
import com.medman.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by jessedavila on 1/17/17.
 */
@Controller
//@RequestMapping("/user")
public class UserController extends BaseController {


    @Autowired
    Users usersDao;

    @Autowired
    Roles roles;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/dashboard")
    public String showDash(Model model) {
        // need to add objects for alerts, meds, and dates, so 3 model.addAttribute?
        return "shared/dashboard";
    }

    @GetMapping("/my_doctors")
    public String showMyDoctors(Model model) {
        // model.addAttribute("users", new User()); Need to call on user relationships between patient and doctor.
        // current logged in user's connected users should be called here and it should show their info.
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

    @PostMapping("/register")
    public String registerUser(@Valid User user, Errors validation, Model model){
        if(validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("user", user);
            return "register";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersDao.save(user);
        return "redirect:/about";

    }

    @GetMapping("/login?logout")
    public String logout(){return "redirect:/";}



}
