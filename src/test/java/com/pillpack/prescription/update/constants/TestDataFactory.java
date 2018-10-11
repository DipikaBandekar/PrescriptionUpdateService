package com.pillpack.prescription.update.constants;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pillpack.prescription.update.model.MedicationModel;
import com.pillpack.prescription.update.model.PrescriptionModel;
import com.pillpack.prescription.update.model.UpdatedPrescriptionModel;

public class TestDataFactory {

	public static final String DATE_TIME_FORMAT_STR = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public static final String TIMESTAMP_STR = "2015-10-25T13:49:43.728Z";
	public static final String MEDICATION_ID_VAL = "562cdd706238310003000099";
	public static final String PRESCRIPTION_ID_VAL = "562cddf76238310003020033";
	public static final String NDC_VAL = "ecuzioqsigu";
	public static final String RXCUI_VAL = "92345";
	public static final String MEDICATION_DESC_VAL = "Acetaminophen 297 mg";
	
	public static Throwable throwable = new Throwable();

	/**
	 * get time stamp in "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" format
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp getTimeStamp() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT_STR);
		Date parsedTimeStamp = dateFormat.parse(TIMESTAMP_STR);
		return new java.sql.Timestamp(parsedTimeStamp.getTime());
	}

	/**
	 * Method to get medication model
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static MedicationModel getMedicationModel() throws ParseException {
		MedicationModel model = new MedicationModel();
		model.setActive(Boolean.TRUE);
		model.setCreatedAt(getTimeStamp());
		model.setUpdatedAt(getTimeStamp());
		model.setGeneric(Boolean.FALSE);
		model.setId(MEDICATION_ID_VAL);
		model.setNdc(NDC_VAL);
		model.setRxcui(RXCUI_VAL);
		model.setDescription(MEDICATION_DESC_VAL);
		return model;
	}

	/**
	 * Method to get prescription model
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static PrescriptionModel getPrescriptionModel() throws ParseException {
		PrescriptionModel model = new PrescriptionModel();
		model.setCreatedAt(getTimeStamp());
		model.setId(PRESCRIPTION_ID_VAL);
		model.setMedicationId(MEDICATION_ID_VAL);
		model.setUpdatedAt(getTimeStamp());
		return model;
	}

	/**
	 * Method to get updated prescription model
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static UpdatedPrescriptionModel getUpdatedPrescriptionModel() throws ParseException {
		UpdatedPrescriptionModel model = new UpdatedPrescriptionModel();
		model.setPrescriptionId(PRESCRIPTION_ID_VAL);
		model.setMedicationId(MEDICATION_ID_VAL);
		return model;
	}

	/**
	 * Get test prescription list
	 * 
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static List<PrescriptionModel> getPrescriptionList()
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper obj = new ObjectMapper();
		String prescriptionListStr = new String(
				"[{\"id\":\"562cddf76238310003020000\",\"medication_id\":\"562cddb86238310003010000\","
						+ "\"created_at\":\"2015-10-25T13:49:43.728Z\",\"updated_at\":\"2015-10-25T13:49:43.728Z\"},"
						+ "{\"id\":\"562cde1d6238310003030000\",\"medication_id\":\"562cdd706238310003000000\","
						+ "\"created_at\":\"2015-10-25T13:50:21.550Z\",\"updated_at\":\"2015-10-25T13:50:21.550Z\"}]");
		List<PrescriptionModel> objList = null;
		if (prescriptionListStr != null && prescriptionListStr.length() != 0) {
			objList = obj.readValue(prescriptionListStr, new TypeReference<List<PrescriptionModel>>() {
			});
		}
		return objList;
	}

	/**
	 * Get updated prescription list
	 * 
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static List<UpdatedPrescriptionModel> getUpdatedPrescriptionList()
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper obj = new ObjectMapper();
		String updatedListStr = new String(
				"[{\"prescription_id\":\"562cddf76238310003020000\",\"medication_id\":\"562cdd706238310003000000\"},"
						+ "{\"prescription_id\":\"562cde1d6238310003030000\",\"medication_id\":\"562cdd706238310003000000\"}]");
		List<UpdatedPrescriptionModel> objList = null;
		if (updatedListStr != null && updatedListStr.length() != 0) {
			objList = obj.readValue(updatedListStr, new TypeReference<List<UpdatedPrescriptionModel>>() {
			});
		}
		return objList;
	}

	/**
	 * Convert medication jsonStr to List
	 * 
	 * @param prescriptionListStr
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static List<MedicationModel> getMedicationList() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper obj = new ObjectMapper();
		String medicationStr = new String(
				"[{\"id\":\"562cdd706238310003000000\",\"ndc\":\"ecuzioqsigu\",\"rxcui\":\"12345\","
						+ "\"description\":\"Acetaminophen 297 mg\",\"generic\":true,\"active\":true,\"created_at\":\"2015-10-25T13:47:28.572Z\","
						+ "\"updated_at\":\"2015-10-25T13:47:28.572Z\"},{\"id\":\"562cddb86238310003010000\",\"ndc\":\"ufietuinycf\",\"rxcui\":\"12345\","
						+ "\"description\":\"Tylenol 297 mg\",\"generic\":false,\"active\":true,\"created_at\":\"2015-10-25T13:48:40.516Z\","
						+ "\"updated_at\":\"2015-10-25T13:48:40.516Z\"}]");
		List<MedicationModel> objList = null;
		if (medicationStr != null && medicationStr.length() != 0) {
			objList = obj.readValue(medicationStr, new TypeReference<List<MedicationModel>>() {
			});
		}
		return objList;
	}

}
