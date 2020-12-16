package com.itcps2.filling.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.itcps2.filling.model.User;
import com.itcps2.repository.UserRepository;
import com.itcps2.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/rest/users")
public class UserController {

    @Autowired
    UserRepository userRepositoru;
//    UserRepository usersRepository;

    @GetMapping(value = "/all")
    public  Iterable<User> getAll() {
        return userRepositoru.findAll();
    }

}