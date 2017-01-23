package com.medman.controllers;

import com.medman.models.*;
import com.medman.repositories.PrescriptionRepository;
import org.apache.tomcat.util.http.parser.MediaType;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

    @Autowired
    PrescriptionRepository prescriptionsDao;

    @Autowired
    Medications medicationsDao;

    @Autowired
    Appointments appointmentsDao;

    @Autowired
    Messages messageDao;


    @GetMapping("/dashboard")
    public String showDash(Model model) {

        model.addAttribute("user", loggedInUser());
        model.addAttribute("prescription", new Prescription());
        model.addAttribute("appointment", new AppointmentTime());
        model.addAttribute("prescriptions", prescriptionsDao.findByPatient(loggedInUser().getId()));
        return "shared/dashboard";
    }

    @PostMapping("/dashboard")
    public String addMedication(
            @Valid Prescription prescription,
            Errors validation,
            Model model
    ) {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("prescriptions", prescription);
            return "shared/dashboard";
        }
        prescription.setUser(loggedInUser());
        prescription.setMedication(medicationsDao.findOne((long) 3));
        prescriptionsDao.save(prescription);
        model.addAttribute("prescriptions", new Prescription());
        return "redirect:/dashboard";
    }

    @PostMapping("/dashboard/medTaken")
    public String takenMed(@Valid Prescription prescription, @RequestParam("id") Long id  ) {
        Prescription currentPr = prescriptionsDao.findOne(id);
        System.out.print(currentPr);
        long ppd;
        ppd = currentPr.getPrescribedQuantity()/currentPr.getDaySupply();
        if (currentPr.getPillsTaken() >= ppd) {
            currentPr.setPillsTaken((long) 0);
            currentPr.setDaySupply(currentPr.getDaySupply()-1);
        } else {
            currentPr.setPillsTaken(currentPr.getPrescribedQuantity()+1);
        }

        prescriptionsDao.save(currentPr);

        return "redirect:/dashboard";
    }

    @GetMapping("/my_doctors")
    public String showMyDoctors(Model model) {
        // model.addAttribute("users", new User()); Need to call on user relationships between patient and doctor.
        // current logged in user's connected users should be called here and it should show their info.
        return "shared/viewLinkedUsers";
    }

    @GetMapping("/messages")
    public String showMessages(Model model) {
        model.addAttribute("message", new Message());
        return "shared/messages";
    }

    @PostMapping("/messages")
    public String sendMessage(
            @Valid Message message,
            Errors validation,
            Model model
    ) {
        if (validation.hasErrors()) {
            System.out.println(message.getMessageContent());
            model.addAttribute("errors", validation);
            model.addAttribute("message", message);
            return "shared/dashboard";
        }
//        message.setUser(loggedInUser()); work this in somehow
        messageDao.save(message);
        return "redirect:/dashboard";

    }

    @GetMapping("/edit")
    public String editPage(Model model) {
        User user = usersDao.findOne(loggedInUser().getId());
        if(user.getId() != loggedInUser().getId()){
            return "/login";
        }
        model.addAttribute("user", user);
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

        User newUser = usersDao.findByUsername(loggedInUser().getUsername());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setDateOfBirth(user.getDateOfBirth());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setStreetAddress(user.getStreetAddress());
        newUser.setCity(user.getCity());
        newUser.setState(user.getState());
        newUser.setZipCode(user.getZipCode());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());


        usersDao.save(newUser);
        return "redirect:/dashboard";

    }

    @PostMapping("/register")
    public String registerUser(
            @Valid User user,
            Errors validation,
            Model model
    ) {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("user", user);
            return "register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersDao.save(user);
        return "redirect:/about";

    }

    @GetMapping("/login?logout")
    public String logout() {
        return "redirect:/";
    }

    @PostMapping("/addAppointment")
    public String addAppointment(@Valid AppointmentTime appointmentTime, Errors validation, Model model) {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("appointment", appointmentTime);
            return "shared/dashboard";
        }

        appointmentTime.setUser(loggedInUser());
        appointmentsDao.save(appointmentTime);
        model.addAttribute("appointment", new AppointmentTime());
        return "redirect:/dashboard";
    }
}



