//package com.medman.twilio.notifications.domain.twilio;
//
///**
// * Created by jessedavila on 1/25/17.
// */// Install the Java helper library from twilio.com/docs/java/install
//
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//
//import java.net.URISyntaxException;
//
//public class SmsSender {
//
//    // Find your Account Sid and Auth Token at twilio.com/console
//    public static final String ACCOUNT_SID = "ACcc5f312b7be36dd69c45525408c3eee7";
//    public static final String AUTH_TOKEN = "f1d2a67bbb61d55f035f12837f1be60a";
//
//    public static void main(String[] args) throws URISyntaxException {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//        Message message = Message
//                .creator(new PhoneNumber("+18306608842"),  // to
//                        new PhoneNumber("+18303023468"),  // from
//                        "Where's Wallace?")
//                .create();
//    }
//}