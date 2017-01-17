package com.medman.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {


    private Long id;

    private String firstName;

    private String lastName;

    private LocalDateTime dateOfBirth;

    private String phoneNumber;

    private String streetAddress;

    private String city;

    private String state;

    private Long zipCode;

    private String username;

    private String password;

    private String email;

    private String profileImgUrl;

    private Long generated_identifier;

    private boolean accountVerified;

    private String npiNumber;



}
