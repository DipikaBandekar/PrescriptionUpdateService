package com.pillpack.prescription.update.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.pillpack.prescription.update.constants.TestDataFactory;

@RunWith(SpringRunner.class)
public class MedicationModelTest {

	/**
	 * Test getter setter methods
	 * 
	 * @throws ParseException
	 */
	@Test
	public void testMedicationModel() throws ParseException {
		// set values
		MedicationModel model = TestDataFactory.getMedicationModel();
		// test medication values
		assertEquals(TestDataFactory.MEDICATION_DESC_VAL, model.getDescription());
		assertEquals(TestDataFactory.MEDICATION_ID_VAL, model.getId());
		assertEquals(TestDataFactory.NDC_VAL, model.getNdc());
		assertEquals(TestDataFactory.RXCUI_VAL, model.getRxcui());
		assertEquals(TestDataFactory.getTimeStamp(), model.getUpdatedAt());
		assertEquals(TestDataFactory.getTimeStamp(), model.getCreatedAt());
		assertEquals(Boolean.TRUE, model.getActive());
		assertEquals(Boolean.FALSE, model.getGeneric());
	}

	/**
	 * test medication model parameterized constructor
	 * 
	 * @throws ParseException
	 */
	@Test
	public void testConstructor() throws ParseException {
		MedicationModel model = new MedicationModel(TestDataFactory.MEDICATION_ID_VAL, TestDataFactory.NDC_VAL,
				TestDataFactory.RXCUI_VAL, TestDataFactory.MEDICATION_DESC_VAL, Boolean.FALSE, Boolean.TRUE,
				TestDataFactory.getTimeStamp(), TestDataFactory.getTimeStamp());
		assertNotNull(model.toString());
	}

}
