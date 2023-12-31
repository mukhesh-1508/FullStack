package com.example.FullStack.controller;

import com.example.FullStack.dto.ForgotPassDTO;
import com.example.FullStack.model.User;
import com.example.FullStack.repository.UserRepository;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserRepository userRepository;


    @GetMapping(path = "/login")
    public String login(){
        return "login";
    }
    @GetMapping(path = "/forgotemail")
    public String forgotemail(){
        return "forgotemail";
    }
    @GetMapping(path = "/confirm")
    public String confirm(){
        return "confirm";
    }
    @GetMapping(path = "/signup")
    public String signup(){
        return "signup";
    }
    @GetMapping(path = "/userform")
    public String userform(){
        return "userform";
    }
    @GetMapping(path = "/view")
    public String view(){
        return "view";
    }


    @GetMapping("/form")
    public String start(Model model) {

        Page<User> userList = userRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "date")));
        model.addAttribute("userList", userList);


        return "form";
    }

    @RequestMapping(path = "/checkEmail",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {
                    MediaType.APPLICATION_ATOM_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
//    @GetMapping("/checkEmail")
    public String checkEmail(Model model,User user){
        User user1 = userRepository.findByEmail(user.getEmail());
        if(user.getEmail().equals("admin@gmail.com")){
            return  "redirect:/user/form";
        }

        if(user1!=null){
            if(user.getPassword().equals(user1.getPassword())){
                model.addAttribute("user",userRepository.findByEmail(user.getEmail()));
                return "userform";
            }else{
                return "login";
            }
        }else{
            return "login";
        }
    }

    @RequestMapping(path = "/createUser",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {
                    MediaType.APPLICATION_ATOM_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public String createUser(User user) {

        user.setDate(new Date());
        System.out.println(user);
        if (userRepository.findByEmail(user.getEmail()) == null) {
            userRepository.save(user);

        }
        return "login";

    }

    @RequestMapping(path = "/updateUser/{email_Id}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {
                    MediaType.APPLICATION_ATOM_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public String updateUser(@PathVariable String email_Id, User userDetails) {
        System.out.println(email_Id);
        System.out.println(userDetails);
        User updateUser = userRepository.findByEmail(email_Id);
        System.out.println(updateUser.toString());
        updateUser.setFirstName(userDetails.getFirstName());
        updateUser.setLastName(userDetails.getLastName());
        updateUser.setDateOfBirth(String.valueOf(userDetails.getDateOfBirth()));
        updateUser.setPhoneNo(userDetails.getPhoneNo());
        updateUser.setAddress(userDetails.getAddress());
        updateUser.setDate(new Date());
        System.out.println(updateUser);
        userRepository.save(updateUser);
        return "redirect:/user/form";
    }

    @RequestMapping(path = "/deleteUser/{email_Id}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {
                    MediaType.APPLICATION_ATOM_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public String deleteUser(@PathVariable String email_Id) {
        System.out.println(email_Id);
        User user = userRepository.findByEmail(email_Id);

        userRepository.delete(user);
//        userRepository.save(user);

        return "redirect:/user/form";

    }

    @RequestMapping(path = "/forget/{emailId}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {
                    MediaType.APPLICATION_ATOM_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public String redirect(@RequestParam String emailId,Model model) {

        System.out.println(emailId);

        User user=userRepository.findByEmail(emailId);
        model.addAttribute("email",emailId);
//        String link="http://localhost:8080/user/confirmPage?email="+emailId;
        String link="http://localhost:8080/user/confirmPage?"+emailId;
        SimpleMailMessage  message=new SimpleMailMessage();
        message.setFrom("mukheshg1508@gmail.com");
        message.setTo(emailId);
        message.setText(link);
        mailSender.send(message);

        return "view";
    }
    @GetMapping(path = "/confirmPage")
    public String savePassword() {
        return "confirm";
    }

    @PostMapping("/reset/{email}/{password}")
    public String reset(@RequestParam String email,@RequestParam String password){
        System.out.println(email + " " + password);
        User user=userRepository.findByEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return "login";
    }





}
