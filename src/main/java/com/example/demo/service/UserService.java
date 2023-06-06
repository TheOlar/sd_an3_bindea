package com.example.demo.service;


import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    static Long lastId = 10L;

    @Autowired
    UserRepository userRepository;


    public User createUser(User user) {
        Long userId = lastId++;
        user.setUserId(userId);
        user.setRating(0.0F);
        user.setRol(Role.USER);
        userRepository.save(user);
        return user;
    }

    public List<User> retrieveUsers() {  //getAllUsers
        return (List<User>) userRepository.findAll();
    }

    public UserDTO retrieveUserById(Long cnp) { //READuSER

        Optional<User> user = userRepository.findById(cnp);
        if(user.isPresent()) {
            return new UserDTO(user.get().getUserId(), user.get().getEmail(),user.get().getFirstName(), user.get().getLastName(),
                    "",user.get().getPhone(), user.get().getRating());
        } else {
            return null;
        }
    }

    public void deleteUserById(long parseLong) {
        userRepository.deleteById(parseLong);
    }



    public User updateUser(Long id, User user) {
        Optional<User> user1 = userRepository.findById(id);
        if(user1.isPresent()) {
            User oldUser = user1.get();
            if(user.getEmail() != null)  oldUser.setEmail(user.getEmail());
            if(user.getPhone() != null)  oldUser.setPhone(user.getPhone());
            if(user.getRating() != null) oldUser.setRating(user.getRating());
            if(user.getLastName() != null) oldUser.setLastName(user.getLastName());
            if(user.getFirstName() != null) oldUser.setFirstName(user.getFirstName());
            if(user.getRol() != null) oldUser.setRol(user.getRol());
            return userRepository.save(oldUser);
        } else {
            return null; // ? save new data or not?
        }

    }

    public boolean saveUser(User user) {
        User a = userRepository.save(user);
        if(a!=null) {
            return true;
        }
        return false;
    }



}
