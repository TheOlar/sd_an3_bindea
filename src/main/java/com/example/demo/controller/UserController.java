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


    //verifica terminalu cand face postman figuri

    //http://localhost:8080/users/getAll
    //proiectu tre sa fie activ sa mearga



    @GetMapping( "/getAll")
    @ResponseBody
    public List<User> retrieveUsers() {  ///read
        return userService.retrieveUsers();
    }


    @GetMapping("/getById/{id}") //  /getById/5    //cooler read
    @ResponseBody
    public User retrieveById(@PathVariable String id){

        return userService.retrieveUserById(Long.parseLong(id));
    }

    @GetMapping("/deleteById/{id}")
    @ResponseBody
    public String deleteById(@PathVariable String id){   ///delete
        userService.deleteUserById(Long.parseLong(id));
        return "Success";
    }

    /*
    * si tre sa intru in body sa pun raw si json  la text si asa il pun
    *
    {
        "userId": 1,
        "lastName": "Bindea1",
        "firstName": "Bogdan1",
        "email": "test1@yahoo.com"
    }

    * */

    @PostMapping("/save")  ////create
    @ResponseBody
    public String saveUser(@RequestBody User user){
        if(userService.saveUser(user))
            return "Success";
        return "Fail";
    }


    @PostMapping("/update")  ////update
    @ResponseBody
    public String updateUser(@RequestBody User user){

        if(userService.saveUser(user)) {
            return "Success";
        }
        return "Fail";

    }

}
