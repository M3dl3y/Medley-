package com.medman.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.instance.Call;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GreetingController {

    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "ACaff88bc37e7ed52a4980273b456d28ef";
    public static final String AUTH_TOKEN = "462fdbe629d195e949bdf65e491a529a";
    public static final String TWILIO_NUMBER = "+12103611492";

    public void sendSMS() {
        try {
            TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

            // Build a filter for the MessageList
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Body", "Hello, Welcome from Medly... pineApples!"));
            params.add(new BasicNameValuePair("To", "+12103727930")); //Add real number here
            params.add(new BasicNameValuePair("From", TWILIO_NUMBER));

            MessageFactory messageFactory = client.getAccount().getMessageFactory();
            Message message = messageFactory.create(params);
            System.out.println(message.getSid());
        }
        catch (TwilioRestException e) {
            System.out.println(e.getErrorMessage());
        }
    }

    @RequestMapping("/greeting")
    public String greeting(
            @RequestParam(value="mode", required=false, defaultValue="text") String mode,
            @RequestParam(value="number", required=true) String number, Model model) {
        model.addAttribute("number", number);
        model.addAttribute("mode", mode);

        if(mode.equalsIgnoreCase("text")){
            sendSMS(number);
        }
        else if (mode.equalsIgnoreCase("call")) {
            makeCall(number);
        }
        return "shared/dashboard";
    }

    public void makeCall() {
        try {
            TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Url", "https://brodan.biz/call.xml"));
            params.add(new BasicNameValuePair("To", "+17069879703")); //Add real number here
            params.add(new BasicNameValuePair("From", TWILIO_NUMBER));

            CallFactory callFactory = client.getAccount().getCallFactory();
            Call call = callFactory.create(params);
        }
        catch (TwilioRestException e) {
            System.out.println(e.getErrorMessage());
        }
    }
}
