package com.gogenie.util.service.impl;

import com.gogenie.util.exceptiom.GoGenieUtilityServiceException;

import com.gogenie.util.service.SMSTextService;
import java.io.IOException;
import java.util.Date;

import org.jsmpp.InvalidResponseException;
import org.jsmpp.PDUException;
import org.jsmpp.bean.Alphabet;
import org.jsmpp.bean.BindType;
import org.jsmpp.bean.ESMClass;
import org.jsmpp.bean.GeneralDataCoding;
import org.jsmpp.bean.MessageClass;
import org.jsmpp.bean.NumberingPlanIndicator;
import org.jsmpp.bean.RegisteredDelivery;
import org.jsmpp.bean.SMSCDeliveryReceipt;
import org.jsmpp.bean.TypeOfNumber;
import org.jsmpp.extra.NegativeResponseException;
import org.jsmpp.extra.ResponseTimeoutException;
import org.jsmpp.session.BindParameter;
import org.jsmpp.session.SMPPSession;
import org.jsmpp.util.AbsoluteTimeFormatter;
import org.jsmpp.util.TimeFormatter;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.IncomingPhoneNumberFactory;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.IncomingPhoneNumber;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;


public class SMSTextServiceImpl implements SMSTextService {

	private static TimeFormatter timeFormatter = new AbsoluteTimeFormatter();
	
	public boolean sendAVerificationCodeToCustomer(String phonenumber) throws GoGenieUtilityServiceException {
		SMPPSession session = new SMPPSession();
		try {
			session.connectAndBind("localhost", 8080, new BindParameter(BindType.BIND_TX, "test", "test", "cp",
					TypeOfNumber.UNKNOWN, NumberingPlanIndicator.UNKNOWN, null));
		} catch (IOException e) {
			System.err.println("Failed connect and bind to host");
			e.printStackTrace();
		}

		try {
			String messageId = session.submitShortMessage("CMT", TypeOfNumber.INTERNATIONAL,
					NumberingPlanIndicator.UNKNOWN, "1616", TypeOfNumber.INTERNATIONAL, NumberingPlanIndicator.UNKNOWN,
					phonenumber, new ESMClass(), (byte) 0, (byte) 1, timeFormatter.format(new Date()), null,
					new RegisteredDelivery(SMSCDeliveryReceipt.DEFAULT), (byte) 0,
					new GeneralDataCoding(Alphabet.ALPHA_DEFAULT, MessageClass.CLASS1, false), (byte) 0,
					"jSMPP simplify SMPP on Java platform".getBytes());
			
			System.out.println("Message submitted, message_id is " + messageId);
		} catch (PDUException e) {
			System.err.println("Invalid PDU parameter");
			e.printStackTrace();
		} catch (ResponseTimeoutException e) {
			System.err.println("Response timeout");
			e.printStackTrace();
		} catch (InvalidResponseException e) {
			System.err.println("Receive invalid respose");
			e.printStackTrace();
		} catch (NegativeResponseException e) {
			System.err.println("Receive negative response");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IO error occur");
			e.printStackTrace();
		}
		session.unbindAndClose();
		return false;
	}

	public static void main(String[] args) {
		try {
//			new SMSTextServiceImpl().sendAVerificationCodeToCustomer("+18048686560");
			new SMSTextServiceImpl().sendAnSMS();
		} catch (GoGenieUtilityServiceException e) {
			e.printStackTrace();
		}
	}

	public void sendAnSMS() throws GoGenieUtilityServiceException {
		
		TwilioRestClient client = new TwilioRestClient("AC40ea25eed54c99bac95da940158081b1", "39fa99c6f6272baed0306bd5d11f6272");
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
		    params.add(new BasicNameValuePair("VoiceUrl", "http://demo.twilio.com/docs/voice.xml"));
		    params.add(new BasicNameValuePair("PhoneNumber", "+14342890505"));
		    
		    
		    IncomingPhoneNumberFactory numberFactory = client.getAccount().getIncomingPhoneNumberFactory();
		    IncomingPhoneNumber number = numberFactory.create(params);
		    System.out.println(number.getSid());
		} catch (Exception e) {
			throw new GoGenieUtilityServiceException(e, "sendAnSMS");
		}
	}
}
