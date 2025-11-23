package com.example.ompay.config;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.phoneNumber}")
    private String twilioPhoneNumber;

    public TwilioConfig() {}

    @Value("${twilio.accountSid}")
    public void initTwilio(String sid) {
        Twilio.init(accountSid, authToken);
    }

    public String getTwilioPhoneNumber() {
        return twilioPhoneNumber;
    }
}
