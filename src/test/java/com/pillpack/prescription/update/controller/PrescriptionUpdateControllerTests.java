package com.pillpack.prescription.update.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.pillpack.prescription.update.constants.TestDataFactory;
import com.pillpack.prescription.update.service.IPrescriptionUpdateService;

@RunWith(SpringRunner.class)
public class PrescriptionUpdateControllerTests {
	
	@TestConfiguration
	static class PrescriptionUpdateControllerTestConfiguration {

		@Bean
		public PrescriptionUpdateController updateController() {
			return new PrescriptionUpdateController();
		}
	}

	@Autowired
	@InjectMocks
	private PrescriptionUpdateController controller;

	@MockBean
	private IPrescriptionUpdateService service;

	/**
	 * Testing controller
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRetreiveData() throws Exception {
		when(service.preparePrescriptionUpdate()).thenReturn(TestDataFactory.getUpdatedPrescriptionList());
		assertEquals(HttpStatus.OK.value(), controller.getPrescriptionUpdates().getStatusCode().value());
	}
}
