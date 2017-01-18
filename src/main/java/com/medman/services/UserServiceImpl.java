package com.medman.services;

import com.medman.models.Role;
import com.medman.models.User;
import com.medman.repositories.RoleRepository;
import com.medman.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(s, s);

        if (user == null)
            throw new UsernameNotFoundException("no such user");

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public boolean emailExists(String email) {
        return false;
    }

    @Override
    public boolean usernameExists(String username) {
        return false;
    }

    @Override
    public void register(User user) {

    }

    @Override
    public void changeEmail(String newEmail, String currentPassword) throws AuthException {

    }

    @Override
    public void changePassword(String newPassword, String currentPassword) throws AuthException {

    }

    @Override
    public void changeProfileInfo(User newProfileInfo) {

    }

    @Override
    public void authenticate(User user) {

    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public boolean isAdmin() {
        return false;
    }

    @Override
    public boolean isDoctor() {
        return false;
    }

    @Override
    public User currentUser() {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
