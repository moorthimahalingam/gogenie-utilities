package com.gogenie.util.service;

import com.gogenie.util.exceptiom.GoGenieUtilityServiceException;

public interface EncryptionService {

	public String encryptedValue (String inputValue) throws GoGenieUtilityServiceException;
	
	public boolean validateEncryptedValue(String inputValue, String encryptedValue) throws GoGenieUtilityServiceException;
}
