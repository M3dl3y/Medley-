package com.medman.models;

import com.medman.utils.LocalDateTimePersistenceConverter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    public interface CreateValidationGroup {}
    public interface ChangeEmailValidationGroup {}
    public interface ChangePasswordValidationGroup {}
    public interface ProfileInfoValidationGroup {}

    @Id
    @GeneratedValue
    private Long Id;

    @Column(nullable = false, length = 50)
    @NotBlank(groups = {CreateValidationGroup.class}, message = "Please enter your first name.")
    private String firstName;

    @Column(nullable = false, length = 50)
    @NotBlank(groups = {CreateValidationGroup.class}, message = "Please enter your last name.")
    private String lastName;

    @Column(nullable = true)
    @Size(min = 10, message = "Please enter your 10 digit phone number")
    private String phoneNumber;

    @Column(unique = true, nullable = false, length = 50)
    // haven't figured out how to specify messages for Size.List in the messages file
    @Size.List({
            @Size(min = 3, message = "Username too short", groups = {CreateValidationGroup.class}),
            @Size(max = 25, message = "Username too long", groups = {CreateValidationGroup.class})
    })
    @NotBlank(groups = {CreateValidationGroup.class})
    @Pattern(regexp = "^[\\p{L}0-9\\._\\- ]+$", groups = {CreateValidationGroup.class})
    private String username;

    @Column(nullable = false, length = 80)
    @Size.List({
            @Size(min = 6, message = "Password too short", groups = {CreateValidationGroup.class, ChangePasswordValidationGroup.class}),
            @Size(max = 80, message = "Password too long", groups = {CreateValidationGroup.class, ChangePasswordValidationGroup.class})
    })
    @NotBlank(groups = {CreateValidationGroup.class, ChangePasswordValidationGroup.class})
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    @Column(unique = true, nullable = true, length = 50)
    @Email(groups = {CreateValidationGroup.class, ChangeEmailValidationGroup.class})
    @NotBlank(groups = {CreateValidationGroup.class, ChangeEmailValidationGroup.class})
    private String email;

    @Column(nullable = true)
    private String smallAvatarLink;

    @Column(nullable = true)
    private String bigAvatarLink;

    @Column(nullable = true)
    private Long generated_identifier;


    @Column(nullable = true)
    private String npiNumber;

    @Column
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime lastLoginDate;

    @OneToOne
    private Role role = new Role(3);


    @OneToMany(mappedBy = "user")
    private List<Prescription> prescriptions;

    @OneToMany(mappedBy = "user")
    private List<Reminder> reminders;

    @OneToMany(mappedBy = "user")
    private List<AppointmentTime> appointmentTimes;

    public User(){}

    public User(User user) {
        Id = user.Id;
        firstName = user.firstName;
        lastName = user.lastName;
        phoneNumber = user.phoneNumber;
        username = user.username;
        password = user.password;
        email = user.email;
        role = user.role;

    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public List<AppointmentTime> getAppointmentTimes() {
        return appointmentTimes;
    }

    public void setAppointmentTimes(List<AppointmentTime> appointmentTimes) {
        this.appointmentTimes = appointmentTimes;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public String getSmallAvatarLink() {
        return smallAvatarLink;
    }

    public void setSmallAvatarLink(String smallAvatarLink) {
        this.smallAvatarLink = smallAvatarLink;
    }

    public String getBigAvatarLink() {
        return bigAvatarLink;
    }

    public void setBigAvatarLink(String bigAvatarLink) {
        this.bigAvatarLink = bigAvatarLink;
    }

    public Long getGenerated_identifier() {
        return generated_identifier;
    }

    public void setGenerated_identifier(Long generated_identifier) {
        this.generated_identifier = generated_identifier;
    }

    public String getNpiNumber() {
        return npiNumber;
    }

    public void setNpiNumber(String npiNumber) {
        this.npiNumber = npiNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
