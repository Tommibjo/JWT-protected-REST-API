/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jwt_auth.controllers.service;

import com.mycompany.jwt_auth.controllers.service.respositories.Account;
import com.mycompany.jwt_auth.controllers.service.respositories.JwtUserDetailsRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author tommib
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private JwtUserDetailsRepository jwtUserDetailsRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        System.out.println("========================");
        System.out.println("Luokka: JwtUserDetailsService implements UserDetailsService");
        System.out.println("Metodi: loadUserByUsername");
        System.out.println("========================");
        Account account = jwtUserDetailsRepository.findByUsername(username);
        if (account == null) {
            System.out.println("EI LÖYDY " + account);
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("USER")));
    }

}
