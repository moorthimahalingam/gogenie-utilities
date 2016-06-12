package com.gogenie.util.service;

import com.gogenie.util.exceptiom.GoGenieUtilityServiceException;

public interface EncryptionService {

	public String hashedValue (String inputValue) throws GoGenieUtilityServiceException;
	
	public boolean validateHashedValue(String inputValue, String encryptedValue) throws GoGenieUtilityServiceException;
}
