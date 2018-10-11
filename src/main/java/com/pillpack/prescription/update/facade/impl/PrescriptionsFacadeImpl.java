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
import com.pillpack.prescription.update.model.PrescriptionModel;

@Component
@Qualifier("prescriptionsFacade")
public class PrescriptionsFacadeImpl implements IPrescriptionUpdateFacade {

	private static final Logger logger = LogManager.getLogger(MedicationsFacadeImpl.class);

	private static final String CLASS_NAME = "PrescriptionsFacadeImpl";

	@Autowired
	private RestTemplate restTemplate;

	@Value(IConstants.PRECRIPTIONS_URI_STR)
	private String prescriptionURI;

	/**
	 * Method used to get prescription list using REST API call
	 */
	@Override
	public Object processRest() throws PrescriptionUpdateException {
		List<PrescriptionModel> prescriptionList = null;
		try {
			prescriptionList = restTemplate.exchange(prescriptionURI, HttpMethod.GET, HttpEntity.EMPTY,
					new ParameterizedTypeReference<List<PrescriptionModel>>() {
					}).getBody();
		} catch (Exception e) {
			logger.error(CLASS_NAME + "-- processRest -- Error occurred while Retrieval!" + e);
			throw new PrescriptionUpdateException(e);
		}
		return prescriptionList;
	}

}
