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

	/**
	 * Method to prepare the updated Prescription list
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UpdatedPrescriptionModel> preparePrescriptionUpdate() throws PrescriptionUpdateException {
		List<UpdatedPrescriptionModel> updatedPrescriptionList = new ArrayList<>();
		Map<String, Map<String, Integer>> rxcuiMedIdCountMap = null;
		Map<String, MedicationModel> medicationMap = new HashMap<>();
		// get prescription list
		List<PrescriptionModel> prescriptionList = (List<PrescriptionModel>) prescriptionFacade.processRest();
		// get medication list
		List<MedicationModel> medicationList = (List<MedicationModel>) medicationsFacade.processRest();
		// prepare medication map to get medicationID
		medicationList.forEach(model -> medicationMap.put(model.getId(), model));
		// load rxcui, medicationId and count of medication id
		rxcuiMedIdCountMap = prepareRXCUIMedIdMap(medicationList);
		for (PrescriptionModel prescriptionModel : prescriptionList) {
			MedicationModel medicationModel = medicationMap.get(prescriptionModel.getMedicationId());
			// rxcui identifier present and medicationId count > 1, replace prescription with generic
			if (rxcuiMedIdCountMap.containsKey(medicationModel.getRxcui())) {
				Map<String, Integer> medicationCountMap = rxcuiMedIdCountMap.get(medicationModel.getRxcui());
				String medicationId = medicationCountMap.keySet().iterator().next();
				if (medicationCountMap.get(medicationId) > 1) {
					updatedPrescriptionList.add(updatePrescriptionModel(prescriptionModel, medicationId));
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
	 * prepare rxcui Medication Id Map
	 * 
	 * @param medicationList
	 * @return
	 */
	private Map<String, Map<String, Integer>> prepareRXCUIMedIdMap(List<MedicationModel> medicationList) {
		Map<String, Map<String, Integer>> rxcuiMedIdCountMap = new HashMap<>();
		Map<String, Integer> medicationCountMap = null;
		for (MedicationModel model : medicationList) {
			if (!rxcuiMedIdCountMap.containsKey(model.getRxcui())) {
				medicationCountMap = new HashMap<>();
				medicationCountMap.put(model.getId(), 1);
				rxcuiMedIdCountMap.put(model.getRxcui(), medicationCountMap);
			} else {
				medicationCountMap = rxcuiMedIdCountMap.get(model.getRxcui());
				String medicationId = medicationCountMap.keySet().iterator().next();
				medicationCountMap.put(medicationId, medicationCountMap.get(medicationId) + 1);
				rxcuiMedIdCountMap.put(model.getRxcui(), medicationCountMap);
			}
		}
		return rxcuiMedIdCountMap;
	}

}
