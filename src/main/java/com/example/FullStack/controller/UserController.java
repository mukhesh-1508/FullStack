package com.example.FullStack.controller;

import com.example.FullStack.exception.ResourceNotFoundException;
import com.example.FullStack.model.User;
import com.example.FullStack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/getAllUsers")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    @PostMapping("/createUser")
    public User createUser(@RequestBody User user){
        System.out.println(user.toString());
        return userRepository.save(user);
    }
    @PutMapping("/updateUser/{email_Id}")
    public ResponseEntity<User> updateUser(@PathVariable String email_Id, @RequestBody User userDetails){
        User updateUser=userRepository.findByEmail(email_Id);
        updateUser.setFirstName(userDetails.getFirstName());
        updateUser.setLastName(userDetails.getLastName());
        updateUser.setEmail(userDetails.getEmail());

        userRepository.save(updateUser);

        return ResponseEntity.ok(updateUser);
    }
    @DeleteMapping("/deleteUser/{Id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long Id){
        User deleteUser=userRepository.findById(Id).orElseThrow(()->new ResourceNotFoundException("There is no data from this id: "+Id));


        userRepository.delete(deleteUser);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
