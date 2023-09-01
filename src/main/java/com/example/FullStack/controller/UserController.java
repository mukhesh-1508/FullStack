package com.example.FullStack.controller;

import com.example.FullStack.exception.ResourceNotFoundException;
import com.example.FullStack.model.User;
import com.example.FullStack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("")
    public String start(Model model){
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList",userList);
        return "index";
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUser(Model model){
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList",userList);
        return ResponseEntity.ok(userList);
    }
    @RequestMapping(path = "/createUser",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {
                    MediaType.APPLICATION_ATOM_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public String createUser(User user){

        userRepository.save(user);
        return "redirect:/user";
    }

    @RequestMapping(path = "/updateUser/{email_Id}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {
                    MediaType.APPLICATION_ATOM_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public String updateUser(@PathVariable String email_Id,User userDetails){
        User updateUser=userRepository.findByEmail(email_Id);
        System.out.println(userDetails);
        updateUser.setFirstName(userDetails.getFirstName());
        updateUser.setLastName(userDetails.getLastName());
        updateUser.setEmail(userDetails.getEmail());
        updateUser.setAddress(userDetails.getAddress());
        updateUser.setDateOfBirth(String.valueOf(userDetails.getDateOfBirth()));
        updateUser.setPhoneNo(userDetails.getPhoneNo());
        userRepository.save(updateUser);
        return "redirect:/user";
    }
    @RequestMapping(path = "/deleteUser/{email_Id}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {
                    MediaType.APPLICATION_ATOM_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public  String deleteUser(@PathVariable String email_Id){
        User user = userRepository.findByEmail(email_Id);

        userRepository.delete(user);

        return "redirect:/user";

    }
}
