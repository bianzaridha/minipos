/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsp.pos.service;

import com.bsp.pos.model.UserPOS;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService{
    
    UserService us = new UserService();
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPOS user = us.getByUsername(username);

            if (user != null) {
                    return new User(user.getUsername(), user.getPassword(),
                                    new ArrayList<>());
            } else {
                    throw new UsernameNotFoundException("User not found with username: " + username);
            }
    }
    
}
