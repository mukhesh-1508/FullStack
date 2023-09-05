package com.example.FullStack.controller;

import com.example.FullStack.model.User;
import com.example.FullStack.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.NotNull;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }
   @Test
    public void getUserTest(){
        Model model = mock(Model.class);
        User user = new User("prasath@gmail.com","prasath","ganesan","909090900","domlur","2023-03-12",new Date());

        List<User> userList = new ArrayList<>();
       when(userRepository.findAll()).thenReturn(userList);

       doReturn(model).when(model).addAttribute(any(),any());

        Assertions.assertEquals(userList,userController.getAllUser(model));

    }
    @Test
    public void addUser_success(){
        Model model=mock(Model.class);
        User user=new User();
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        String response=userController.createUser(user);
        Assert.assertEquals("redirect:/user?message="+ "Added",response);
    }
    @Test
    public void addUser_fail(){
        Model model=mock(Model.class);
        User user = new User("prasath@gmail.com","prasath","ganesan","909090900","domlur","2023-03-12",new Date());

        when(userRepository.findByEmail(anyString())).thenReturn(user);
        String response=userController.createUser(user);
        Assert.assertEquals("redirect:/user?message="+ "Failed",response);
    }
    @Test
    public void delete_success(){
        Model model=mock(Model.class);

        User user = new User("prasath@gmail.com","prasath","ganesan","909090900","domlur","2023-03-12",new Date());
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        String response=userController.deleteUser("prasath@gmail.com");
        Assert.assertEquals("redirect:/user",response);

    }
    @Test
    public void update_success(){
        Model model=mock(Model.class);
        User user=new User("prasath@gmail.com","prasath","ganesan","909090900","domlur","2023-03-12",new Date());
        when(userRepository.findByEmail(anyString())).thenReturn(user);

        String response=userController.updateUser("prasath@gmail.com",user);

        Assert.assertEquals("redirect:/user",response);

    }
}