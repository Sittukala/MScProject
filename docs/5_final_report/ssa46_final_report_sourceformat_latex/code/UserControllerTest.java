package com.buzzfeed.project.Controller;

import com.buzzfeed.project.Service.DNewsService;
import com.buzzfeed.project.Service.UserService;
import com.buzzfeed.project.config.SecurityConfig;
import com.buzzfeed.project.domain.DNews;
import com.buzzfeed.project.domain.Source;
import com.buzzfeed.project.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@Import(SecurityConfig.class)
class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void addUserTest() throws Exception {

        User user1 = new User();
        user1.setUid(1);
        user1.setEmailId("user1@gmail.com");
        user1.setPhoneNumber("0993004922");
        user1.setPassword("hry12@S");
        user1.setUserName("user");
        List<String> preferredCategoryList = new ArrayList<String>();

        preferredCategoryList.add("Health");
        user1.setPreferredCategory(preferredCategoryList);
        user1.setGender("Male");
        user1.setLocation(("NZ"));
        user1.setFavoriteList(null);
        user1.setLikedList(null);
        user1.setSavedList(null);

        when(userService.addUser(user1)).thenReturn(user1);

        assertEquals("user", userService.addUser(user1).getUserName());
    }

    @Test
    public void findUserTest() throws Exception {

        User user1 = new User();
        user1.setUid(1);
        user1.setEmailId("user1@gmail.com");
        user1.setPhoneNumber("0993004922");
        user1.setPassword("hry12@S");
        user1.setUserName("user");
        List<String> preferredCategoryList = new ArrayList<String>();

        preferredCategoryList.add("Health");
        user1.setPreferredCategory(preferredCategoryList);
        user1.setGender("Male");
        user1.setLocation(("NZ"));
        user1.setFavoriteList(null);
        user1.setLikedList(null);
        user1.setSavedList(null);

        userService.addUser(user1);

        when(userService.findUser(1)).thenReturn(user1);

        assertEquals("user", userService.findUser(1).getUserName());
    }

    @Test
    public void findUserFailTest() throws Exception {

        when(userService.findUser(1)).thenReturn(null);

        assertEquals(null, userService.findUser(1));
    }


    @Test
    public void updateUserTest() throws Exception {

        User user1 = new User();
        user1.setUid(1);
        user1.setEmailId("user1@gmail.com");
        user1.setPhoneNumber("0993004922");
        user1.setPassword("hry12@S");
        user1.setUserName("user");
        List<String> preferredCategoryList = new ArrayList<String>();

        preferredCategoryList.add("Health");
        user1.setPreferredCategory(preferredCategoryList);
        user1.setGender("Male");
        user1.setLocation(("NZ"));
        user1.setFavoriteList(null);
        user1.setLikedList(null);
        user1.setSavedList(null);

        userService.addUser(user1);
        when(userService.findUser(1)).thenReturn(user1);
        assertEquals("user", userService.findUser(1).getUserName());


        user1.setUserName("user1updated");
        userService.addUser(user1);
        when(userService.findUser(1)).thenReturn(user1);
        assertEquals("user1updated", userService.findUser(1).getUserName());
    }



}