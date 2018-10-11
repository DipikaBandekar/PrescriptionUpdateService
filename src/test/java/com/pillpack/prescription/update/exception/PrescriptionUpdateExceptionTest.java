package com.pillpack.prescription.update.exception;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.pillpack.prescription.update.constants.TestDataFactory;

public class PrescriptionUpdateExceptionTest {

	@Test
	public void testLegacyLoanStrategyExceptionTest() {

		assertNotNull(new PrescriptionUpdateException());

		assertNotNull(new PrescriptionUpdateException("Exception"));

		assertNotNull(new PrescriptionUpdateException(TestDataFactory.throwable));

		assertNotNull(new PrescriptionUpdateException("Exception", 5000, TestDataFactory.throwable));

	}
}
