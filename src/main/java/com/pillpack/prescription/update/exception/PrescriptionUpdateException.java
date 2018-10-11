package com.pillpack.prescription.update.exception;

public class PrescriptionUpdateException extends Exception {
	
	/**
	 *  Prescription Update Service Checked Exceptions
	 */

	private static final long serialVersionUID = 1L;

	public PrescriptionUpdateException() {
		super();
	}

	public PrescriptionUpdateException(String message, Integer code, Throwable cause) {
		super(message + " Error Code " + code, cause);
	}

	public PrescriptionUpdateException(String message) {
		super(message);
	}

	public PrescriptionUpdateException(Throwable cause) {
		super(cause);
	}
}
