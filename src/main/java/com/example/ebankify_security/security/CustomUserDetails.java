package com.example.ebankify_security.security;

import com.example.ebankify_security.domain.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails extends User implements UserDetails {

}
