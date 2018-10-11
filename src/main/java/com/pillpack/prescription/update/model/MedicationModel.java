package com.pillpack.prescription.update.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MedicationModel implements Serializable {
	/**
	 * Medication Model class
	 */

		private static final long serialVersionUID = 1L;

		private String id;
		private String ndc;
		private String rxcui;
		private String description;
		private Boolean generic;
		private Boolean active;
		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
		@JsonProperty("created_at")
		private Timestamp createdAt;
		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
		@JsonProperty("updated_at")
		private Timestamp updatedAt;
		
}
