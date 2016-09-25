package com.gogenie.util.service;

public interface EncryptionService {

	public String hashedValue (String inputValue);
	
	public boolean validateHashedValue(String inputValue, String encryptedValue);
}
