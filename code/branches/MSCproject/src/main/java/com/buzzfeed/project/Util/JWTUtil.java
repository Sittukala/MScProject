package com.buzzfeed.project.Util;

import com.buzzfeed.project.Repository.UserRepository;
import com.buzzfeed.project.Service.JWTUserDetailsService;
import com.buzzfeed.project.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JWTUtil{

    @Autowired
    private JWTUserDetailsService service;

    @Autowired
    private UserRepository userRepository;


    private String secret = "javatechie";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        final String authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        System.out.println("Claims" +claims);
        System.out.println("Auth" +authorities);
        User jobSeeker = userRepository.findByUserName(userDetails.getUsername());
        Integer id = jobSeeker.getUid();
        //  UserDetails userDetails_id = (UserDetails) jobSeekerRepository.findByUserName(userDetails.getUsername());


//        UserDetails userDetails_id = service.loadUserByUsername(userDetails.getUsername());
        claims.put("Id", id);
        System.out.println("Claims_new" +claims);
       // claims.put("Role", authorities);

        //new code
        //  User springUser = (User)authResult.getPrincipal();


        return createToken(id,claims, userDetails.getUsername());
    }

    private String createToken(Integer id,Map<String, Object> claims, String subject) {


        TreeMap<String, Object> sorted = new TreeMap<>();

        // Copy all data from hashMap into TreeMap
        sorted.putAll(claims);

        // Display the TreeMap which is naturally sorted
        for (Map.Entry<String, Object> entry : sorted.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());

        System.out.println("Claims before create token" +sorted);
        return Jwts.builder().setId(String.valueOf(id)).setClaims(sorted).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256,  secret).compact(); // changed from HS256 to HS512
    }

    public Boolean validateToken(String token, UserDetails userDetails) {

        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
