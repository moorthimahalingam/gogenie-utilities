package com.gogenie.util.service;

import com.gogenie.util.exceptiom.GoGenieUtilityServiceException;

public interface SMSTextService {

	public boolean sendAVerificationCodeToCustomer(String phonenumber) throws GoGenieUtilityServiceException;
}
