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

    }
}
