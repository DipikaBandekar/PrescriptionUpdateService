package com.pillpack.prescription.update.service;

import java.util.List;

import com.pillpack.prescription.update.exception.PrescriptionUpdateException;
import com.pillpack.prescription.update.model.UpdatedPrescriptionModel;

@FunctionalInterface
public interface IPrescriptionUpdateService {
	
	/**
	 * Method to prepare updated prescription list
	 * 
	 * @return
	 * @throws PrescriptionUpdateException
	 */
	public List<UpdatedPrescriptionModel> preparePrescriptionUpdate() throws PrescriptionUpdateException;
}
