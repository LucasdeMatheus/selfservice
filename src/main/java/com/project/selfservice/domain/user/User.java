package com.project.selfservice.domain.user;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String login, String password) {
    }
    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return login;  // Use the actual login
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Adjust as needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Adjust as needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Adjust as needed
    }

    @Override
    public boolean isEnabled() {
        return true;  // Adjust as needed
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                ", login='" + login + '\'' +
                ", id=" + id +
                '}';
    }


}
