/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jwt_auth.controllers.service.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author tommib
 */
public interface JwtUserDetailsRepository extends MongoRepository<Account,String> {
   Account findByUsername(String username);
}