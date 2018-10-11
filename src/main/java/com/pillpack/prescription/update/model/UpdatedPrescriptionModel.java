package com.pillpack.prescription.update.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@JsonInclude(value = Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdatedPrescriptionModel implements Serializable {
	/**
	 * Updated Prescription Model class
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("prescription_id")
	private String prescriptionId;
	@JsonProperty("medication_id")
	private String medicationId;

	@Override
	public int hashCode() {
		return prescriptionId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof UpdatedPrescriptionModel))
			return false;
		if (obj == this)
			return true;
		UpdatedPrescriptionModel model = (UpdatedPrescriptionModel) obj;
		return this.getPrescriptionId().equals(model.getPrescriptionId());
	}

}
