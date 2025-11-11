package com.example.ompay.dto.request;

import jakarta.validation.constraints.NotBlank;

public class OtpValidationDTO {
    @NotBlank(message = "Le téléphone est obligatoire")
    private String telephone;

    @NotBlank(message = "Le code OTP est obligatoire")
    private String otp;

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getOtp() { return otp; }
    public void setOtp(String otp) { this.otp = otp; }
}
