package com.example.ompay.service.implement;

import com.example.ompay.config.TwilioConfig;
import com.example.ompay.service.INotificationService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("TwilioService")
@RequiredArgsConstructor
public class TwilioService implements INotificationService {

    private final TwilioConfig twilioConfig;

    @Override
    public void send(String to, String message) {
        Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(twilioConfig.getTwilioPhoneNumber()),
                message
        ).create();
    }
}

