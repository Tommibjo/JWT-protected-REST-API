/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jwt_auth.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tommib
 */
@RestController
public class RandomController {

    @GetMapping("/hello")
    public String helloworld() {
        return "Hello world!";
    }
}
