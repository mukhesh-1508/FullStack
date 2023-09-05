package com.example.FullStack.controller;

import com.example.FullStack.exception.ResourceNotFoundException;
import com.example.FullStack.model.User;
import com.example.FullStack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping("")
    public String start(Model model,@RequestParam(required = false) String message){
        List<User> userList = userRepository.findAll(Sort.by("date").descending());
        model.addAttribute("userList",userList);
        model.addAttribute("res",message);
        return "form";
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUser(Model model){

        List<User> userList = userRepository.findAll(Sort.by("date").descending());
        model.addAttribute("userList",userList);
        return userList;
    }
    @RequestMapping(path = "/createUser",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {
                    MediaType.APPLICATION_ATOM_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public String createUser(User user){

        user.setDate(new Date());
        System.out.println(user);
        if(userRepository.findByEmail(user.getEmail())==null){
            userRepository.save(user);
            return "redirect:/user?message="+ "Added";
        }
        return "redirect:/user?message="+ "Failed";
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
        System.out.println(updateUser.toString());
        updateUser.setFirstName(userDetails.getFirstName());
        updateUser.setLastName(userDetails.getLastName());
        updateUser.setDateOfBirth(String.valueOf(userDetails.getDateOfBirth()));
        updateUser.setPhoneNo(userDetails.getPhoneNo());
        updateUser.setDate(new Date());
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
