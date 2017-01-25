package com.medman.controllers;

import com.medman.models.*;
import com.medman.repositories.PrescriptionRepository;
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

//    @Autowired
//    MedicationRepository medicationsDao;

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
        model.addAttribute("appointments", appointmentsDao.findByPatient(loggedInUser().getId()));

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
        System.out.println(currentPr);
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
        if (user.getId() != loggedInUser().getId()) {
            return "/login";
        }
        loggedInUser().setUsername(user.getUsername());
        model.addAttribute("user", user);
        return "shared/profile"; // only a logged in user can go to user/edit

    }

//    @PostMapping("/editLoginCred")
//    public String editUserDetailForm(
//            @Valid User user,
//            Errors validation,
//            Model model
//    ){
//        if(validation.hasErrors()){
//            model.addAttribute("errors", validation);
//            model.addAttribute("user", user);
//            return "shared/edit";
//        }
//        loggedInUser().setUsername(user.getUsername());
//        User existingUser = usersDao.findByUsername(loggedInUser().getUsername());
//        existingUser.setUsername(user.getUsername());
//        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
//        usersDao.save(existingUser);
//        return "redirect:/dashboard";
//    }



    @PostMapping("/editUserDetail")
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
        return "redirect:/login";

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
//            return "shared/dashboard";
        }

        appointmentTime.setUser(loggedInUser());
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
        return "redirect:/dashboard";
    }


    @PostMapping("/editAppointment")
    public String editPrescription(@RequestParam(value = "id") Long id, @RequestParam(value = "name") String name, @RequestParam(value = "appointmentDate") String appointmentDate,
                                   @RequestParam(value = "appointmentTime") String appointmentTime, @RequestParam(value = "notes") String notes, Model model){


        AppointmentTime appointment = appointmentsDao.findOne(id);
        appointment.setName(name);

        DateFormat formatter = new SimpleDateFormat("HH:mm");

        try{
            Date newdate = new SimpleDateFormat().parse(appointmentDate);
            appointment.setAppointmentDate(newdate);
        }catch(Exception e){

        }
        try{
            Time newTime = new Time(formatter.parse(appointmentTime).getTime());
            appointment.setAppointmentTimes(newTime);
        }catch (Exception e){

        }

        appointment.setNotes(notes);
        appointmentsDao.save(appointment);
        return "redirect:/dashboard";
    }


}



