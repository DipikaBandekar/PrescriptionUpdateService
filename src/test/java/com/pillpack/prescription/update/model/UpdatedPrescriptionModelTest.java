package com.pillpack.prescription.update.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Test;

import com.pillpack.prescription.update.constants.TestDataFactory;

public class UpdatedPrescriptionModelTest {

	/**
	 * Test getter setter methods
	 * 
	 * @throws ParseException
	 */
	@Test
	public void testPrescriptionModel() throws ParseException {
		// set values
		UpdatedPrescriptionModel model = TestDataFactory.getUpdatedPrescriptionModel();
		// test updated prescription values
		assertEquals(TestDataFactory.PRESCRIPTION_ID_VAL, model.getPrescriptionId());
		assertEquals(TestDataFactory.MEDICATION_ID_VAL, model.getMedicationId());
	}

	/**
	 * test updated prescription model parameterized constructor
	 * 
	 * @throws ParseException
	 */
	@Test
	public void testConstructor() throws ParseException {
		UpdatedPrescriptionModel model = new UpdatedPrescriptionModel(TestDataFactory.PRESCRIPTION_ID_VAL,
				TestDataFactory.MEDICATION_ID_VAL);
		assertNotNull(model.toString());
	}
	
	/**
	 * test updated prescription model hashCode and equals method
	 * 
	 * @throws ParseException
	 */
	@Test
	public void testHashCodeEquals() throws ParseException {
		UpdatedPrescriptionModel model1 = TestDataFactory.getUpdatedPrescriptionModel();
		UpdatedPrescriptionModel model2 = TestDataFactory.getUpdatedPrescriptionModel();
		assertEquals(model1.hashCode(), model2.hashCode());
		assertTrue(model1.equals(model2));
	}
}
