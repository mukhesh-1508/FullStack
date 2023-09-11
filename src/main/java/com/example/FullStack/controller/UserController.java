package com.example.FullStack.controller;

import com.example.FullStack.model.User;
import com.example.FullStack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
     @GetMapping("/signup")
    public String signup() {
        return "signup";
    }




    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/checkEmail")
    public String checkEmail(User user){
         User user1 = userRepository.findByEmail(user.getEmail());
         if(user1!=null){
             if(user.getPassword().equals(user1.getPassword())){
                 return "user";
             }else{
                 return "login";
             }
         }else{
             return "login";
         }
    }




    @GetMapping("/form")
    public String start(Model model) {
        System.out.println("HIIIII");
        Page<User> userList = userRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "date")));
        model.addAttribute("userList", userList);

        return "form";
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
        User updateUser = userRepository.findByEmail(email_Id);
        System.out.println(updateUser.toString());
        updateUser.setFirstName(userDetails.getFirstName());
        updateUser.setLastName(userDetails.getLastName());
        updateUser.setDateOfBirth(String.valueOf(userDetails.getDateOfBirth()));
        updateUser.setPhoneNo(userDetails.getPhoneNo());
        updateUser.setDate(new Date());
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
        User user = userRepository.findByEmail(email_Id);

        userRepository.delete(user);

        return "redirect:/user/form";

    }
}
