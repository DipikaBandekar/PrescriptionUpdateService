package com.pillpack.prescription.update.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pillpack.prescription.update.model.PrescriptionModel;
import com.pillpack.prescription.update.model.UpdatedPrescriptionModel;
import com.pillpack.prescription.update.service.IPrescriptionUpdateService;

import io.swagger.annotations.ApiOperation;

@RestController
public class PrescriptionUpdateController {

	private static final Logger logger = LogManager.getLogger(PrescriptionUpdateController.class);

	@Autowired
	private IPrescriptionUpdateService service;

	/**
	 * Method to get the updated prescription list
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(value = "/docs/1.0/prescription/updates", produces = "application/json")
	@ApiOperation(httpMethod = "GET", value = "This Api Operation is used to retrieve updated prescription list", response = PrescriptionModel.class)
	public ResponseEntity getPrescriptionUpdates() {
		ResponseEntity response = null;
		try {
			List<UpdatedPrescriptionModel> list = service.preparePrescriptionUpdate();
			Map<String, List<UpdatedPrescriptionModel>> map = new HashMap<>();
		    map.put("prescription_updates", list);
			response = new ResponseEntity(map, HttpStatus.OK);
		} catch (Exception e) {
			logger.info(e);
			response = new ResponseEntity("Exception occurred while processing the request! " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
		return response;
	}
}
