package com.medman.services;

import com.medman.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.security.auth.message.AuthException;

public interface UserService extends UserDetailsService{

    User findByEmail (String email);

    User findByUsername(String, username);

    boolean emailExists(String email);

    boolean usernameExists(String username);

    void register(User user);

    void changeEmail(String newEmail, String currentPassowrd) throws AuthException;

    void changePassword(String newPassword, String currentPassword) throws AuthException;

    void changeProfileInfo(User newProfileInfo);

    void changeAvatar(UploadedAvatarInfo uploadedAvatarInfo);

    void removeAvatar();

    void authenticate(User user);

    boolean isAuthenticated();

    boolean isAdmin();

    boolean isDoctor();

    User currentUser();

}
