package com.medman.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;


public class UserWithRoles extends User implements UserDetails {
    private List<String> userRoles;

    public UserWithRoles(User user, List<String> userRoles) {
        super(user);
        this.userRoles = userRoles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static interface UserRepository extends JpaRepository<User, Long> {

        User findByUsernameIgnoreCase(String username);

        User findByEmailIgnoreCase(String email);

        User findByUsernameOrEmail(String username, String email);
    }

    public static interface PrescriptionRepository extends CrudRepository<Prescription, Long> {
    }

//    public static interface MedicationRepository extends CrudRepository<Medication, Long> {
//
//    }
}
