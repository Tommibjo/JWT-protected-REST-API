/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jwt_auth.controllers;

import com.mycompany.jwt_auth.config.JwtTokenUtil;
import com.mycompany.jwt_auth.controllers.service.JwtUserDetailsService;
import com.mycompany.jwt_auth.controllers.service.respositories.JwtResponse;
import com.mycompany.jwt_auth.controllers.service.respositories.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tommib
 */
@RestController
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody Account account) {
        System.out.println("========================");
        System.out.println("Luokka: JwtAuthenticationController");
        System.out.println("Metodi: createAuthenticationToken");
        System.out.println("========================");
        if(authenticate(account.getUsername(), account.getPassword())){
        final UserDetails userDetails = userDetailsService.loadUserByUsername(account.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
        }
        return new ResponseEntity<Error>(HttpStatus.UNAUTHORIZED);
    }

    private boolean authenticate(String username, String password) {
        System.out.println("========================");
        System.out.println("Luokka: JwtAuthenticationController");
        System.out.println("Metodi: authenticate");
        System.out.println("========================");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return true;
        } catch (DisabledException e) {
            System.out.println("Käyttäjä disabloitu: " + e);
            return false;
        } catch (BadCredentialsException e) {
            System.out.println("Käyttäjätunnukset väärin: " + e);
            return false;
        }
    }
}
