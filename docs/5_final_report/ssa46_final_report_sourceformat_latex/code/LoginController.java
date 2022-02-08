package com.buzzfeed.project.Controller;

import com.buzzfeed.project.Service.JWTUserDetailsService;
import com.buzzfeed.project.Util.JWTUtil;
import com.buzzfeed.project.domain.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LoginController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private JWTUserDetailsService jwtUserDetailsService;


    @Autowired
    private AuthenticationManager authenticationManager;


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/")
    public String greet() {
        return "Welcome !!!!";
    }

    //authenticate users with username and password and generates token if the successful authentication.
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("invalid username/password");
        }

        final UserDetails userDetails = jwtUserDetailsService
                .loadUserByUsername(authRequest.getUserName());


        return jwtUtil.generateToken(userDetails);
    }
}