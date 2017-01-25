package com.medman.controllers;

import com.medman.models.*;
import com.medman.models.PrescriptionRepository;
import org.apache.tomcat.util.http.parser.MediaType;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by jessedavila on 1/17/17.
 */
@Controller
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
    MedicationRepository medicationsDao;

    @Autowired
    Appointments appointmentsDao;

    @Autowired
    ReminderRepository remindersDao;

    @Autowired
    Messages messageDao;

    @Autowired
    DoctorPatients docPatientDao;


    @GetMapping("/dashboard")
    public String showDash(Model model) {

        model.addAttribute("user", loggedInUser());
        model.addAttribute("prescription", new Prescription());
        model.addAttribute("appointment", new AppointmentTime());
        model.addAttribute("prescriptions", prescriptionsDao.findByPatient(loggedInUser().getId()));
        model.addAttribute("medications", medicationsDao.findAll());
        model.addAttribute("lowSupplyPrescriptions", prescriptionsDao.findByDaySupplyAlert(loggedInUser().getId()));
        prescriptionsDao.findByDaySupplyAlert(loggedInUser().getId()).forEach(prescription -> {
            System.out.println("id: " + prescription.getId());
            System.out.println("user_id: " + prescription.getUser().getId());
            System.out.println("how many left: " + prescription.getDaySupply());
        });
        // add to model list of prescriptions with low daysSupply to display in alert panel
        model.addAttribute("prescriptions", new Prescription());
        model.addAttribute("appointments", new AppointmentTime());
        model.addAttribute("medications", new Medication());
        model.addAttribute("reminders", new Reminder());
        model.addAttribute("savedPrescriptions", prescriptionsDao.findByPatient(loggedInUser().getId()));
        model.addAttribute("savedAppointments", appointmentsDao.findByUserId(loggedInUser().getId()));
        model.addAttribute("savedMedications", medicationsDao.findAll());
        model.addAttribute("savedReminders", remindersDao.findAll());

        return "shared/dashboard";
    }

    @PostMapping("/addPrescription")
    public String addMedication(
            @Valid Prescription prescription,
            @RequestParam(name = "medicationId") Long medicationId,
            Errors validation,
            Model model
    ) {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("prescriptions", prescription);
            return "shared/dashboard";
        }
        prescription.setUser(loggedInUser());
        prescription.setMedication(medicationsDao.findOne(medicationId));
        prescriptionsDao.save(prescription);
        model.addAttribute("prescriptions", new Prescription());
        return "redirect:/dashboard";
    }

    @PostMapping("/dashboard/medTaken")
    public String takenMed(@RequestParam("id") Long id) {
        Prescription currentPr = prescriptionsDao.findOne(id);
        if (currentPr.getDosageFrequency() == 0) {
            currentPr.setDosageFrequency(currentPr.getPrescribedQuantity()/currentPr.getDaySupply());
        }
        currentPr.setPillsTaken(currentPr.getPillsTaken() + 1);
        if (currentPr.getPillsTaken().equals(currentPr.getDosageFrequency())) {
            currentPr.setPillsTaken((long) 0);
            currentPr.setDaySupply(currentPr.getDaySupply() - 1);
        }
        prescriptionsDao.save(currentPr);

        return "redirect:/dashboard";
    }

    @GetMapping("/my_doctors")
    public String showMyDoctors(Model model) {
        List<User> myUsers = new ArrayList<>();
        List<Long> patientIds = docPatientDao.findByPatient(loggedInUser().getId());
        for (Long patient : patientIds) {
            System.out.println("patient id " + patient);
            myUsers.add(usersDao.findOne(patient));
        }
        model.addAttribute("users", myUsers);
        return "shared/viewLinkedUsers";
    }

    @PostMapping("/my_doctors")
    public String setDoctor(@RequestParam("docKey") Long docKey) {
        DoctorPatientRelationship dpr = new DoctorPatientRelationship();
        dpr.setPatient(loggedInUser().getId());
        dpr.setDoctor(usersDao.findByDocNum(docKey).getId());
        docPatientDao.save(dpr);
        return "shared/viewLinkedUsers";
    }

    @GetMapping("/messages")
    public String showMessages(Model model) {
        model.addAttribute("message", new Message());

        List<User> myUsers = new ArrayList<>();
        List<Long> patientIds = docPatientDao.findByPatient(loggedInUser().getId());
        for (Long patient : patientIds) {
            System.out.println("patient id " + patient);
            myUsers.add(usersDao.findOne(patient));
        }
        model.addAttribute("users", myUsers);
        // take the doctors id and find the dpr where patient matches loggedInUser and doctor matches user.id;

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
        message.setUser(loggedInUser());
        message.setDpr(docPatientDao.findOne(1l)); // this is hardcoded
        messageDao.save(message);
        return "redirect:/dashboard";

    }

    @GetMapping("/edit")
    public String editPage(Model model) {
        User user = usersDao.findOne(loggedInUser().getId());
        if (user.getId() != loggedInUser().getId()) {
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
//        newUser.setDateOfBirth(user.getDateOfBirth());
//        newUser.setPhoneNumber(user.getPhoneNumber());
//        newUser.setStreetAddress(user.getStreetAddress());
//        newUser.setCity(user.getCity());
//        newUser.setState(user.getState());
//        newUser.setZipCode(user.getZipCode());
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
        System.out.println("Add appointment");
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("appointments", appointmentTime);
//            return "shared/dashboard";
        }

        appointmentTime.setUser(loggedInUser());
        appointmentsDao.save(appointmentTime);
        model.addAttribute("appointments", new AppointmentTime());
        return "redirect:/dashboard";
    }

    @PostMapping("/addReminder")
    public String addReminder(@Valid Reminder reminder, Errors validation, Model model) {
        System.out.println("Add reminder");
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("reminders", reminder);
//            return "shared/dashboard";
        }

        reminder.setUser(loggedInUser());
        remindersDao.save(reminder);
        model.addAttribute("reminders", new Reminder());
        return "redirect:/dashboard";
    }
}



