package com.pillpack.prescription.update.facade.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.pillpack.prescription.update.constants.IConstants;
import com.pillpack.prescription.update.exception.PrescriptionUpdateException;
import com.pillpack.prescription.update.facade.IPrescriptionUpdateFacade;
import com.pillpack.prescription.update.model.MedicationModel;

@Component
@Qualifier("medicationsFacade")
public class MedicationsFacadeImpl implements IPrescriptionUpdateFacade {

	private static final Logger logger = LogManager.getLogger(MedicationsFacadeImpl.class);

	private static final String CLASS_NAME = "MedicationsFacadeImpl";

	@Autowired
	private RestTemplate restTemplate;

	@Value(IConstants.MEDICATIONS_URI_STR)
	private String medicationsURI;

	/**
	 * Method used to get medication list using REST API call
	 */
	@Override
	public Object processRest() throws PrescriptionUpdateException {
		List<MedicationModel> medicationList = null;
		try {
			medicationList = restTemplate.exchange(medicationsURI, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<MedicationModel>>() {
			}).getBody();
		} catch (Exception e) {
			logger.error(CLASS_NAME + "-- processRest -- Error occurred while Retrieval!" + e);
			throw new PrescriptionUpdateException(e);
		}
		return medicationList;
	}

}
