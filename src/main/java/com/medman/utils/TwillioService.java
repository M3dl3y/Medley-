package com.medman.utils;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Call;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("twillioSvc")
public class TwillioService {

    @Value("${twillio-key}")
    private String ACCOUNT_SID;

    @Value("${twillio-token}")
    private String AUTH_TOKEN;

    @Value("${twillio-phone}")
    private String TWILIO_NUMBER;



    public void sendSMS(String body, String to) {
        try {
            TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

            // Build a filter for the MessageList
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Body", body));
            params.add(new BasicNameValuePair("To", to));
            params.add(new BasicNameValuePair("From", TWILIO_NUMBER));

            MessageFactory messageFactory = client.getAccount().getMessageFactory();
            Message message = messageFactory.create(params);
            System.out.println(message.getSid());
        }
        catch (TwilioRestException e) {
            System.out.println(e.getErrorMessage());
        }
    }

    public void makeCall(String to) {
        try {
            TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Url", "https://brodan.biz/call.xml"));
            params.add(new BasicNameValuePair("To", to));
            params.add(new BasicNameValuePair("From", TWILIO_NUMBER));

            CallFactory callFactory = client.getAccount().getCallFactory();
            Call call = callFactory.create(params);
        }
        catch (TwilioRestException e) {
            System.out.println(e.getErrorMessage());
        }
    }
}
