package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping( "/getAll")
    @ResponseBody
    public List<User> retrieveUsers() {
        return userService.retrieveUsers();
    }


    @GetMapping("/getById/{id}") //  /getById/5
    @ResponseBody
    public User retrieveById(@PathVariable String id){
        return userService.retrieveUserById(Long.parseLong(id));
    }

    @GetMapping("/deleteById/{id}")
    @ResponseBody
    public String deleteById(@PathVariable String id){
        userService.deleteUserById(Long.parseLong(id));
        return "Success";
    }

    @PostMapping("/save")
    @ResponseBody
    public String saveUser(@RequestBody User user){
        if(userService.saveUser(user))
            return "Success";
        return "Fail";
    }

}
