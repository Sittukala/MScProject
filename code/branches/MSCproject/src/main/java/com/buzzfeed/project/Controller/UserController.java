package com.buzzfeed.project.Controller;

import com.buzzfeed.project.Service.UserService;
import com.buzzfeed.project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins="*",  allowedHeaders = "*")
@RestController
@RequestMapping("/api/Users")
public class UserController {


    @Autowired
    private UserService userService;

    @CrossOrigin(origins="*",  allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<User> addJobSeeker(@RequestBody User user ){
        System.out.println("user"+ user);
        System.out.println("user"+ user.getPhoneNumber());
        User newUser = userService.addUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }



    @CrossOrigin(origins="*",  allowedHeaders = "*")

    @GetMapping("/userId/{uid}")
    public ResponseEntity<User> findJobSeeker(@PathVariable Integer uid){
        User user= userService.findUser(uid);

        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @CrossOrigin(origins="*",  allowedHeaders = "*")
    @PutMapping("/userId/{uid}")
    public ResponseEntity<User> updateSeeker(@PathVariable(value = "uid") Integer id,
                                                  @RequestBody User updateSeeker)  {
        User user = userService.findUser(id);


        user.setUserName(updateSeeker.getUserName());
        user.setEmailId(updateSeeker.getEmailId());
        user.setPhoneNumber(updateSeeker.getPhoneNumber());
        user.setLocation(updateSeeker.getLocation());
        user.setPreferredCategory(updateSeeker.getPreferredCategory());

        final User updatedUser = userService.addUser(user);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

}
