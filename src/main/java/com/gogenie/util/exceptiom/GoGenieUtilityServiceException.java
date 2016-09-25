package com.gogenie.util.exceptiom;

public class GoGenieUtilityServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3028007306596625828L;
	
	public GoGenieUtilityServiceException() {
		super();
	}
	
	public GoGenieUtilityServiceException(String message) {
		super(message);
	}
	
	public GoGenieUtilityServiceException(Throwable t, String methodName) {
		super(t);
	}
	
	public GoGenieUtilityServiceException(Exception e, String methodName) {
		super(e);
	}

	public GoGenieUtilityServiceException(Throwable t) {
		super(t);
	}

	public GoGenieUtilityServiceException(Exception e) {
		super(e);
	}


}
