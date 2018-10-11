package com.pillpack.prescription.update.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;

import org.junit.Test;

import com.pillpack.prescription.update.constants.TestDataFactory;

public class PrescriptionModelTest {

	/**
	 * Test getter setter methods
	 * 
	 * @throws ParseException
	 */
	@Test
	public void testPrescriptionModel() throws ParseException {
		// set values
		PrescriptionModel model = TestDataFactory.getPrescriptionModel();
		// test prescription values
		assertEquals(TestDataFactory.PRESCRIPTION_ID_VAL, model.getId());
		assertEquals(TestDataFactory.MEDICATION_ID_VAL, model.getMedicationId());
		assertEquals(TestDataFactory.getTimeStamp(), model.getUpdatedAt());
		assertEquals(TestDataFactory.getTimeStamp(), model.getCreatedAt());
	}

	/**
	 * test prescription model parameterized constructor
	 * 
	 * @throws ParseException
	 */
	@Test
	public void testConstructor() throws ParseException {
		PrescriptionModel model = new PrescriptionModel(TestDataFactory.PRESCRIPTION_ID_VAL, TestDataFactory.MEDICATION_ID_VAL,
				TestDataFactory.getTimeStamp(), TestDataFactory.getTimeStamp());
		assertNotNull(model.toString());
	}
}
