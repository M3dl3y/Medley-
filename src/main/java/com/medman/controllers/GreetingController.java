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

//@Controller
//public class GreetingController {

    // Find your Account Sid and Token at twilio.com/user/account

//
//
//    @RequestMapping("/greeting")
//    public String greeting(
//            @RequestParam(value="mode", required=false, defaultValue="text") String mode, String body,
//            @RequestParam(value="number", required=true) String number, Model model) {
//        model.addAttribute("number", number);
//        model.addAttribute("mode", mode);
//        model.addAttribute("body", body);
//
//        if(mode.equalsIgnoreCase("text")){
//            sendSMS(body, number);
//        }
//        else if (mode.equalsIgnoreCase("call")) {
//            makeCall(number);
//        }
//        return "shared/dashboard";
//    }
//}
