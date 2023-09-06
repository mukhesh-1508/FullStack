package com.example.FullStack.controller;

import com.example.FullStack.model.User;
import com.example.FullStack.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

public class UserControllerTest {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getUserTest() {

        User user1 = new User();
        user1.setDate(new Date(2023 - 01 - 05));
        User user2 = new User();
        user2.setDate(new Date(2023 - 01 - 06));

        List<User> userList = Arrays.asList(user2, user1);
        Pageable pageable = PageRequest.of(0, 10);
        Page<User> ListOfUser = new PageImpl<User>(userList, pageable, userList.size());

        when(userRepository.findAll(
                PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "date")))).thenReturn(ListOfUser);
        Model model = mock(Model.class);
        String allUsers = userController.start(model);

//   doReturn(model).when(model).addAttribute(any(),any());

        Assertions.assertEquals("form", allUsers);

    }
    @Test
    public void getPagination(){

    }

    @Test
    public void addUser_success() {
        Model model = mock(Model.class);
        User user = new User();
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        String response = userController.createUser(user);
        Assert.assertEquals("redirect:/user?message=" + "Added", response);
    }

    @Test
    public void addUser_fail() {
        Model model = mock(Model.class);
        User user = new User("prasath@gmail.com", "prasath", "ganesan", "909090900", "domlur", "2023-03-12", new Date());

        when(userRepository.findByEmail(anyString())).thenReturn(user);
        String response = userController.createUser(user);
        Assert.assertEquals("redirect:/user?message=" + "Failed", response);
    }

    @Test
    public void delete_success() {
        Model model = mock(Model.class);

        User user = new User("prasath@gmail.com", "prasath", "ganesan", "909090900", "domlur", "2023-03-12", new Date());
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        String response = userController.deleteUser("prasath@gmail.com");
        Assert.assertEquals("redirect:/user", response);

    }

    @Test
    public void update_success() {
        Model model = mock(Model.class);
        User user = new User("prasath@gmail.com", "prasath", "ganesan", "909090900", "domlur", "2023-03-12", new Date());
        when(userRepository.findByEmail(anyString())).thenReturn(user);

        String response = userController.updateUser("prasath@gmail.com", user);

        Assert.assertEquals("redirect:/user", response);

    }

    @Test
    public void des() {
        Model model = mock(Model.class);


    }
}