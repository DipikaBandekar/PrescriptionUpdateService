package com.pillpack.prescription.update.facade;

import com.pillpack.prescription.update.exception.PrescriptionUpdateException;

@FunctionalInterface
public interface IPrescriptionUpdateFacade {
	
	/**
	 * Method to process Rest API calls
	 * 
	 * @return
	 * @throws PrescriptionUpdateException
	 */
	public Object processRest(String message) throws PrescriptionUpdateException;
}
