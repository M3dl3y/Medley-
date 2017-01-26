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
import java.sql.Time;
import java.text.DateFormat;
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

//    @Autowired
//    MedicationRepository medicationsDao;

    @Autowired
    Appointments appointmentsDao;

    @Autowired
    ReminderRepository remindersDao;

    @Autowired
    Messages messageDao;

    @Autowired
    DoctorPatients docPatientDao;

    @Autowired
    TwillioService twillioService;


    @GetMapping("/dashboard")
    public String showDash(Model model) {

        model.addAttribute("user", loggedInUser());
        model.addAttribute("prescription", new Prescription());
        model.addAttribute("appointment", new AppointmentTime());
        model.addAttribute("prescriptions", prescriptionsDao.findByPatient(loggedInUser().getId()));
        model.addAttribute("appointments", appointmentsDao.findByPatient(loggedInUser().getId()));


//        model.addAttribute("alertPrescription" , prescriptionsDao.findByUser(loggedInUser().getId()));

        LocalDate today = new LocalDate().now();
        LocalDate toDate = today.plusDays(3);
        model.addAttribute("alertAppointment", appointmentsDao.findByUserAndAppointmentDateBetween(loggedInUser(), today.toDate(), toDate.toDate()));

        return "shared/dashboard";
    }

    @PostMapping("/addPrescription")
    public String addMedication(
            @Valid Prescription prescription,
//            @RequestParam(name = "medicationId") Long medicationId,
            @RequestParam(name = "prescribedDate_submit") String prescribedDate_submit,
            Errors validation,
            Model model
    ) {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("prescriptions", prescription);
            return "shared/dashboard";
        }
        prescription.setUser(loggedInUser());
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
        message.setUser(loggedInUser());
        messageDao.save(message);
        return "redirect:/dashboard";

    }

    @GetMapping("/edit")
    public String editPage(Model model) {
        User user = usersDao.findOne(loggedInUser().getId());
        if (user.getId() != loggedInUser().getId()) {
            return "/login";
        }
        loggedInUser().setUsername(user.getUsername());
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
            return "shared/edit";
        }

        User existingUser = usersDao.findOne(loggedInUser().getId());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        usersDao.save(existingUser);
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
        twillioService.sendSMS("Welcome to twillio", (user.getPhoneNumber()));

        return "redirect:/login";

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
            return "shared/dashboard";
        }

        appointmentTime.setUser(loggedInUser());
        appointmentTime.setAppointmentDate(appointmentTime.getAppointmentDate());
        appointmentsDao.save(appointmentTime);
        model.addAttribute("appointment", new AppointmentTime());
        return "redirect:/dashboard";
    }

    @PostMapping("/editPrescription")
    public String editPrescription(@RequestParam(value = "id") Long id, @RequestParam(value = "name") String name, @RequestParam(value = "date") String date, @RequestParam(value = "strength") String strength,
                                   @RequestParam(value = "sig") String sig, @RequestParam(value = "daySupply") Long daySupply, @RequestParam(value = "prescribedQuantity") Long prescribedQuantity, Model model){

        Prescription pr = prescriptionsDao.findOne(id);
        pr.setName(name);

        try{
            Date newdate = new SimpleDateFormat().parse(date);
            pr.setPrescribedDate(newdate);
        }catch(Exception e){

        }

        pr.setStrength(strength);
        pr.setSig(sig);
        pr.setDaySupply(daySupply);
        pr.setPrescribedQuantity(prescribedQuantity);
        prescriptionsDao.save(pr);
        model.addAttribute("prescriptions", new Prescription());
        return "redirect:/dashboard";
    }


    @PostMapping("/editAppointment")
    public String editPrescription(@RequestParam(value = "id") Long id, @RequestParam(value = "name") String name, @RequestParam(value = "appointmentDate") String appointmentDate,
                                   @RequestParam(value = "appointmentTime") String appointmentTime, @RequestParam(value = "notes") String notes, Model model){


        AppointmentTime appointment = appointmentsDao.findOne(id);
        appointment.setName(name);

        DateFormat timeFormatter = new SimpleDateFormat("HH:mm");

        try{
            Date newdate = new SimpleDateFormat().parse(appointmentDate);
            appointment.setAppointmentDate(newdate);
        }catch(Exception e){

        }
        try{
            Time newTime = new Time(timeFormatter.parse(appointmentTime).getTime());
            appointment.setAppointmentTimes(newTime);
        }catch (Exception e){

        }

        appointment.setNotes(notes);
        appointmentsDao.save(appointment);
        model.addAttribute("appointments", new AppointmentTime());
        return "redirect:/dashboard";
    }


    @GetMapping("/deletePrescription/{id}")
    public String deletePost(@PathVariable long id, @ModelAttribute Prescription prescription){
        Prescription currentPrescription = prescriptionsDao.findOne(id);
        prescriptionsDao.delete(currentPrescription);
        return "redirect:/dashboard";
    }

    @GetMapping("/deleteAppointment/{id}")
    public String deleteAppointment(@PathVariable long id, @ModelAttribute AppointmentTime appointmentTime){
        AppointmentTime currentAppointment = appointmentsDao.findOne(id);
        appointmentsDao.delete(currentAppointment);
        return "redirect:/dashboard";
    }


}



