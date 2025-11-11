// package com.example.ompay.service;

// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import org.springframework.stereotype.Service;
// import java.util.Date;

// @Service
// public class JwtService {

//     private static final String SECRET_KEY = "secret-ompay-1234"; // A stocker en env variable
//     private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10h

//     public String generateToken(String telephone) {
//         return Jwts.builder()
//                 .setSubject(telephone)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                 .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
//                 .compact();
//     }
// }
