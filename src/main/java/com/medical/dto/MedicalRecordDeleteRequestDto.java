package com.medical.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MedicalRecordDeleteRequestDto {
	@NotNull(message = "Doctor id must be supplie")
  private Long doctorId;
}
