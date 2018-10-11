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
public class PrescriptionModel implements Serializable {
	/**
	 * Prescription Model class
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	@JsonProperty("medication_id")
	private String medicationId;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@JsonProperty("created_at")
	private Timestamp createdAt;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@JsonProperty("updated_at")
	private Timestamp updatedAt;
}
