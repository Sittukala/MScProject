package com.buzzfeed.project.Controller;

import com.buzzfeed.project.Service.UserService;
import com.buzzfeed.project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/Users")
public class UserController {
//This Controller deals all the functions with respect to the users.

    @Autowired
    private UserService userService;


    //Add Users to the database after signup process
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User user1 = new User();
        HashMap<String, Double> categorymap = new HashMap<String, Double>();
        List<String> categoryList = new ArrayList<String>();
        List<String> preferredCategoryList = new ArrayList<String>();
        preferredCategoryList.addAll(user.getPreferredCategory());

        //Add all the categories
        categoryList.add("business");
        categoryList.add("science");
        categoryList.add("health");
        categoryList.add("technology");
        categoryList.add("entertainment");
        categoryList.add("sports");

        //Assign category weight with basic weight for all and additional weight based on preferences
        for (String category : categoryList) {
            categorymap.put(category, 0.0);
        }

        System.out.println("prefered" + categoryList + " " + preferredCategoryList);

        for (String element : categoryList) {
            for (String element2 : preferredCategoryList) {
                if (element.equalsIgnoreCase(element2)) {
                    categorymap.put(element, 2.0);

                } else {
                    continue;

                }
            }
        }


        user1.setMap(categorymap);

        user1.setEmailId(user.getEmailId());
        user1.setPhoneNumber(user.getPhoneNumber());
        user1.setPassword(user.getPassword());
        user1.setUserName(user.getUserName());
        user1.setPreferredCategory(user.getPreferredCategory());
        user1.setGender(user.getGender());
        user1.setLocation((user.getLocation()));
        user1.setFavoriteList((user.getFavoriteList()));
        user1.setLikedList((user.getLikedList()));
        user1.setSavedList(user.getSavedList());
        //user1.setMap(categorymap);


//        System.out.println("user"+ user1);
//        System.out.println("user"+ user1.getPhoneNumber());
        User newUser = userService.addUser(user1);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }


    //Finds user with the respective id
    @CrossOrigin(origins = "*", allowedHeaders = "*")

    @GetMapping("/userId/{uid}")
    public ResponseEntity<User> findUser(@PathVariable Integer uid) {
        User user = userService.findUser(uid);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //Updates user whose id is given as parameter with the user body details.
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/userId/update/{uid}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "uid") Integer id,
                                           @RequestBody User updateSeeker) {
        User user = userService.findUser(id);
        System.out.println("update user" + updateSeeker.getMap());

        user.setUserName(updateSeeker.getUserName());
        user.setEmailId(updateSeeker.getEmailId());
        user.setPhoneNumber(updateSeeker.getPhoneNumber());
        user.setLocation(updateSeeker.getLocation());
        user.setPreferredCategory(updateSeeker.getPreferredCategory());
        user.setFavoriteList((updateSeeker.getFavoriteList()));
        user.setLikedList((updateSeeker.getLikedList()));
        user.setSavedList(updateSeeker.getSavedList());
        final User updatedUser = userService.addUser(user);
        //   System.out.println("updated user"+ updatedUser.getLikedList());
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

}
