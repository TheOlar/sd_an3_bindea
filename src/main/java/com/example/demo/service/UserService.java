package com.example.demo.service;


import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public List<User> retrieveUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User retrieveUserById(Long cnp) {

        Optional<User> user = userRepository.findById(cnp);

        if(user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    public void deleteUserById(long parseLong) {
        userRepository.deleteById(parseLong);
    }

    public boolean saveUser(User user) {
        User a = userRepository.save(user);
        if(a!=null) {
            return true;
        }
        return false;
    }



    public boolean updateUser(User user) {
        User a = userRepository.save(user);
        if(a!=null) {
            return true;
        }
        return false;

    }

}
