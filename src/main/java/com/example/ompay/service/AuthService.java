package com.example.ompay.service;

import org.springframework.stereotype.Service;

import com.example.ompay.entity.Compte;
import com.example.ompay.utils.JwtUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class AuthService {

    private final CompteService compteService;
    private final Map<String, String> otpCache = new HashMap<>(); 
    private final JwtUtils jwtUtils;

    public AuthService(CompteService compteService, JwtUtils jwtUtils) {
        this.compteService = compteService;
        this.jwtUtils = jwtUtils;
    }

    // Génération OTP
    public String generateOtp(String telephone) {
        compteService.getCompteByTelephone(telephone)
        .orElseThrow(() -> new RuntimeException("Compte non trouvé"));

        String otp = String.valueOf(new Random().nextInt(9000) + 1000);
        otpCache.put(telephone, otp);
        System.out.println("OTP pour " + telephone + " : " + otp);
        return otp;
    }

    // Validation OTP
    public boolean validateOtp(String telephone, String otp) {
        String storedOtp = otpCache.get(telephone);
        if (storedOtp != null && storedOtp.equals(otp)) {
            otpCache.remove(telephone); // on supprime après validation
            return true;
        }
        return false;
    }

    public Map<String, String> validatePin(String telephone, String pin) {
        Compte compte = compteService.getCompteByTelephone(telephone)
        .orElseThrow(() -> new RuntimeException("Compte non trouvé"));
        if (!compte.getCodeConnexion().equals(pin)) {
            throw new RuntimeException("PIN incorrect !");
        }

        String accessToken = jwtUtils.generateAccessToken(telephone);
        String refreshToken = jwtUtils.generateRefreshToken(telephone);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }
}