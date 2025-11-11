package com.example.ompay.controller;

import com.example.ompay.dto.request.*;
import com.example.ompay.dto.response.ApiResponse;
import com.example.ompay.dto.response.AuthResponseDTO;
// import com.example.ompay.dto.response.AuthResponseDTO;
import com.example.ompay.dto.response.OtpValidationResponseDTO;
import com.example.ompay.dto.response.PhoneResponseDTO;
import com.example.ompay.service.AuthService;
// import com.example.ompay.service.CompteService;
// import com.example.ompay.service.JwtService;
import com.example.ompay.utils.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    // private final JwtService jwtService;
    // private final CompteService compteService;

    public AuthController(AuthService authService) {
        this.authService = authService;
        // this.jwtService = jwtService;
        // this.compteService = compteService;
    }

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody PhoneRequestDTO dto) {
        authService.generateOtp(dto.getTelephone()); // génère et envoie le code
        PhoneResponseDTO response = new PhoneResponseDTO(dto.getTelephone(), "OTP envoyé avec succès");
        return ResponseHandler.success(response.getMessage(), response);
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<?> validateOtp(@RequestBody OtpValidationDTO dto) {
        boolean isValid = authService.validateOtp(dto.getTelephone(), dto.getOtp());
        OtpValidationResponseDTO response = new OtpValidationResponseDTO(
            dto.getTelephone(), 
            isValid ? "OTP correct, vous pouvez maintenant saisir votre code PIN" : "OTP incorrect"
        );

        if (isValid) {
            return ResponseHandler.success(response.getMessage(), response);
        } else {
            return ResponseHandler.error(response.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> validatePin(@RequestBody PinRequestDTO request) {
        Map<String, String> tokens = authService.validatePin(request.getTelephone(), request.getCodeConnexion());

        String accessToken = tokens.get("accessToken");
        String refreshToken = tokens.get("refreshToken");

        // Création des cookies sécurisés
        ResponseCookie accessCookie = ResponseCookie.from("access_token", accessToken)
                .httpOnly(true)
                .secure(true) // mettre false en local si besoin
                .path("/")
                .sameSite("Strict")
                .maxAge(15 * 60) // 15 min
                .build();

        ResponseCookie refreshCookie = ResponseCookie.from("refresh_token", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .sameSite("Strict")
                .maxAge(7 * 24 * 60 * 60) // 7 jours
                .build();

        return ResponseEntity.ok()
                .header("Set-Cookie", accessCookie.toString())
                .header("Set-Cookie", refreshCookie.toString())
                .body(ResponseHandler.success("Connexion réussie", null, HttpStatus.OK).getBody());
    }
}
