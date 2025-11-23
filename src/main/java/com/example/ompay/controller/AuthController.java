package com.example.ompay.controller;

import com.example.ompay.dto.request.*;
import com.example.ompay.dto.response.ApiResponse;
import com.example.ompay.dto.response.AuthResponseDTO;
import com.example.ompay.dto.response.OtpValidationResponseDTO;
import com.example.ompay.dto.response.PhoneResponseDTO;
import com.example.ompay.service.AuthService;
import com.example.ompay.service.TokenBlacklistService;
// import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final TokenBlacklistService tokenBlacklistService;
    // private final JwtService jwtService;
    // private final CompteService compteService;

    public AuthController(AuthService authService, TokenBlacklistService tokenBlacklistService) {
        this.authService = authService;
        this.tokenBlacklistService = tokenBlacklistService;
        // this.jwtService = jwtService;
        // this.compteService = compteService;
    }

    @PostMapping("/send-otp")
    public ApiResponse<?> sendOtp(@RequestBody PhoneRequestDTO dto) {
        authService.generateOtp(dto.getTelephone()); // génère et envoie le code
        PhoneResponseDTO response = new PhoneResponseDTO(dto.getTelephone(), "OTP envoyé avec succès");
        return ApiResponse.success(response.getMessage(), response);
    }

    @PostMapping("/validate-otp")
    public ApiResponse<?> validateOtp(@RequestBody OtpValidationDTO dto) {
        boolean isValid = authService.validateOtp(dto.getTelephone(), dto.getOtp());
        OtpValidationResponseDTO response = new OtpValidationResponseDTO(
            dto.getTelephone(), 
            isValid ? "OTP correct, vous pouvez maintenant saisir votre code PIN" : "OTP incorrect"
        );

        if (isValid) {
            return ApiResponse.success(response.getMessage(), response);
        } else {
            return ApiResponse.error(response.getMessage());
        }
    }

    @PostMapping("/login")
    public ApiResponse<?> validatePin(@RequestBody PinRequestDTO request) {
        Map<String, String> tokens = authService.validatePin(request.getTelephone(), request.getCodeConnexion());

        String accessToken = tokens.get("accessToken");
        String refreshToken = tokens.get("refreshToken");

        AuthResponseDTO authResponse = new AuthResponseDTO(accessToken, refreshToken);

        return ApiResponse.success("Connexion réussie", authResponse);
    }
    @PostMapping("/logout")
    public ApiResponse<?> logout(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            tokenBlacklistService.blacklistToken(token);
        }
        return ApiResponse.success("Déconnexion réussie", null);
    }
}
