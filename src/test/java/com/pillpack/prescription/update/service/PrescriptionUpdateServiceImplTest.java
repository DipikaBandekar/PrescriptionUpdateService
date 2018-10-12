package com.pillpack.prescription.update.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.pillpack.prescription.update.constants.TestDataFactory;
import com.pillpack.prescription.update.exception.PrescriptionUpdateException;
import com.pillpack.prescription.update.facade.IPrescriptionUpdateFacade;
import com.pillpack.prescription.update.model.MedicationModel;
import com.pillpack.prescription.update.model.PrescriptionModel;
import com.pillpack.prescription.update.model.UpdatedPrescriptionModel;
import com.pillpack.prescription.update.service.impl.PrescriptionUpdateServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class PrescriptionUpdateServiceImplTest {

	@TestConfiguration
	static class PrescriptionUpdateServiceImplTestConfiguration {

		@Bean
		public PrescriptionUpdateServiceImpl service() {
			return new PrescriptionUpdateServiceImpl();
		}
	}

	@MockBean
	@Qualifier("medicationsFacade")
	private IPrescriptionUpdateFacade medicationFacade;

	@MockBean
	@Qualifier("prescriptionsFacade")
	private IPrescriptionUpdateFacade prescriptionFacade;
	
	@MockBean
	@Qualifier("rxcuiFacade")
	private IPrescriptionUpdateFacade rxcuiFacade;

	@Autowired
	private PrescriptionUpdateServiceImpl service;

	/**
	 * Testing the scenario to check if genric medications are added to updated list
	 * 
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws PrescriptionUpdateException
	 * @throws IOException
	 */
	@Test
	public void testPreparePrescriptionUpdateGeneric()
			throws JsonParseException, JsonMappingException, PrescriptionUpdateException, IOException {
		when(medicationFacade.processRest("")).thenReturn((List<MedicationModel>) TestDataFactory.getMedicationList());
		when(rxcuiFacade.processRest(Mockito.anyString())).thenReturn((List<MedicationModel>) TestDataFactory.getMedicationList());
		when(prescriptionFacade.processRest(""))
				.thenReturn((List<PrescriptionModel>) TestDataFactory.getPrescriptionList());
		List<UpdatedPrescriptionModel> updatedList = service.preparePrescriptionUpdate();
		List<UpdatedPrescriptionModel> expectedList = TestDataFactory.getUpdatedPrescriptionList();
		for (int i = 0; i < expectedList.size(); i++) {
			assertEquals(expectedList.get(i), (updatedList.get(i)));
		}
	}


}
