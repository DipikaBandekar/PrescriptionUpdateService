package com.pillpack.prescription.update.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pillpack.prescription.update.exception.PrescriptionUpdateException;
import com.pillpack.prescription.update.facade.IPrescriptionUpdateFacade;
import com.pillpack.prescription.update.model.MedicationModel;
import com.pillpack.prescription.update.model.PrescriptionModel;
import com.pillpack.prescription.update.model.UpdatedPrescriptionModel;
import com.pillpack.prescription.update.service.IPrescriptionUpdateService;

@Service
public class PrescriptionUpdateServiceImpl implements IPrescriptionUpdateService {

	@Autowired
	@Qualifier("prescriptionsFacade")
	private IPrescriptionUpdateFacade prescriptionFacade;

	@Autowired
	@Qualifier("medicationsFacade")
	private IPrescriptionUpdateFacade medicationsFacade;

	@Autowired
	@Qualifier("rxcuiFacade")
	private IPrescriptionUpdateFacade rxcuiFacade;

	/**
	 * Method to prepare the updated Prescription list
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UpdatedPrescriptionModel> preparePrescriptionUpdate() throws PrescriptionUpdateException {
		List<UpdatedPrescriptionModel> updatedPrescriptionList = new ArrayList<>();
		Map<String, MedicationModel> medicationMap = new HashMap<>();
		List<MedicationModel> rxcuiList = null;
		// get prescription list
		List<PrescriptionModel> prescriptionList = (List<PrescriptionModel>) prescriptionFacade.processRest("");
		// get medication list
		List<MedicationModel> medicationList = (List<MedicationModel>) medicationsFacade.processRest("");
		// prepare medication map to get medicationID
		medicationList.forEach(model -> medicationMap.put(model.getId(), model));
		for (PrescriptionModel prescriptionModel : prescriptionList) {
			MedicationModel medicationModel = medicationMap.get(prescriptionModel.getMedicationId());
			if (!medicationModel.getGeneric()) {
				// prescription with brand name
				rxcuiList = (List<MedicationModel>) rxcuiFacade.processRest(medicationModel.getRxcui());
				if (rxcuiList.size() > 1) {
					MedicationModel genericModel = getGenericModel(rxcuiList);
					if (genericModel != null)
						updatedPrescriptionList.add(updatePrescriptionModel(prescriptionModel, genericModel.getId()));
				}
			}

		}
		return updatedPrescriptionList;
	}

	/**
	 * helper method to update prescription model
	 * 
	 * @param oldModel
	 * @param medicationId
	 * @return
	 */
	private UpdatedPrescriptionModel updatePrescriptionModel(PrescriptionModel oldModel, String medicationId) {
		UpdatedPrescriptionModel model = new UpdatedPrescriptionModel();
		model.setPrescriptionId(oldModel.getId());
		model.setMedicationId(medicationId);
		return model;
	}

	/**
	 * get generic model from the list
	 * 
	 */
	private MedicationModel getGenericModel(List<MedicationModel> modelList) {
		for (MedicationModel model : modelList) {
			if (model.getGeneric())
				return model;
		}
		return null;
	}

}
