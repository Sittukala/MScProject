package com.buzzfeed.project.Service;

import com.buzzfeed.project.Repository.UserRepository;
import com.buzzfeed.project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
//Service to find, add user objects
    @Autowired
    public UserRepository userRepository;

    public User addUser(User user){

        //  System.out.println("Saved"+ user);
        return userRepository.save(user);
    }

    public User findUser(Integer id){
        return userRepository.findByUid(id);
    }


    public User findrecommends(Integer id){
        return userRepository.findByUid(id);
    }

}