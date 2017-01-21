package com.medman.controllers;

import com.medman.models.Prescription;
import com.medman.models.Users;
import com.medman.services.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private Users users;

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("/prescriptions")
    public String showPrescriptionList(@RequestParam())
}