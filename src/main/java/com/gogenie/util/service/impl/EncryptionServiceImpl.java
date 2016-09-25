package com.gogenie.util.service.impl;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.gogenie.util.service.EncryptionService;

public class EncryptionServiceImpl implements EncryptionService {

	public String hashedValue(String inputValue) {
		String encryptedValue = BCrypt.hashpw(inputValue, BCrypt.gensalt(12));
		return encryptedValue;
	}

	public boolean validateHashedValue(String inputValue, String encryptedValue) {
		if (BCrypt.checkpw(inputValue, encryptedValue)) {
			return true;
		}
		return false;
	}
}
