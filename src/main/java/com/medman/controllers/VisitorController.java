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
        return "register"; // return to user register page.
    }
    @GetMapping("/sitemap")
    public String siteMap(Model model){
        return "sitemap";
    }


}
